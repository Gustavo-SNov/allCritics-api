package com.allcritics.api.service;

import com.allcritics.api.domain.entity.User;
import com.allcritics.api.dto.user.LoginDTO;
import com.allcritics.api.dto.user.RegisterDTO;
import com.allcritics.api.dto.user.UserDTO;
import com.allcritics.api.exception.user.UserAlreadyExistsException;
import com.allcritics.api.pattern.mapper.UserMapper;
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
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final TokenService tokenService;

    @Autowired
    public UserService(UserRepository userRepository,  @Lazy AuthenticationManager authenticationManager, UserMapper userMapper, TokenService tokenService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userMapper = userMapper;
    }

    public  UserDTO login(LoginDTO login) {
        UsernamePasswordAuthenticationToken usernamePassword =  new UsernamePasswordAuthenticationToken(login.email(), login.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        User authenticatedUser = (User) auth.getPrincipal();
        String token = tokenService.generateToken(authenticatedUser);

        UserDTO userDTO = userMapper.toUserDTO(authenticatedUser);
        userDTO.setToken(token);
        return userDTO;
    }

    public UserDTO register(RegisterDTO registerDTO) {
        if (userRepository.existsUserByUsername(registerDTO.username())) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        if (userRepository.existsUserByEmail(registerDTO.email())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User newUser = userMapper.toRegisterUser(registerDTO);

        User savedUser = userRepository.save(newUser);

        return userMapper.toUserDTO(savedUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        return user;
    }
}
