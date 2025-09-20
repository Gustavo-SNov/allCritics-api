package com.allcritics.api.service;

import com.allcritics.api.domain.entity.User;
import com.allcritics.api.dto.user.LoginDTO;
import com.allcritics.api.dto.user.RegisterDTO;
import com.allcritics.api.dto.user.UserDTO;
import com.allcritics.api.exception.AccessDeniedException;
import com.allcritics.api.exception.user.UserAlreadyExistsException;
import com.allcritics.api.pattern.mapper.AuthMapper;
import com.allcritics.api.repository.AuthRepository;
import com.allcritics.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final AuthMapper authMapper;

    @Autowired
    public AuthService(AuthRepository authRepository,@Lazy UserRepository userRepository,@Lazy AuthenticationManager authenticationManager,TokenService tokenService, AuthMapper authMapper) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.authMapper = authMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = authRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        return user;
    }

    public UserDTO register(RegisterDTO registerDTO) {
        if (userRepository.existsUserByUsername(registerDTO.username())) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        if (userRepository.existsUserByEmail(registerDTO.email())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User newUser = authMapper.toRegisterUser(registerDTO);
        User savedUser = userRepository.save(newUser);

        String token = tokenService.generateToken(savedUser);
        return authMapper.toAutheticatedUser(savedUser, token);
    }

    public UserDTO login(LoginDTO login) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(login.email(), login.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        User authenticatedUser = (User) auth.getPrincipal();
        String token = tokenService.generateToken(authenticatedUser);

        return authMapper.toAutheticatedUser(authenticatedUser, token);
    }

    public User validatePermission(String idUser, String username) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if(!user.getUsername().equals(username)) {
            throw new AccessDeniedException("Access denied. You do not have permission to update this user");
        }
        return user;
    }
}
