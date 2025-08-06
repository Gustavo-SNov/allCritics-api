package com.allcritics.api.service;

import com.allcritics.api.domain.entity.User;
import com.allcritics.api.dto.user.LoginDTO;
import com.allcritics.api.dto.user.RegisterDTO;
import com.allcritics.api.dto.user.UserDTO;
import com.allcritics.api.exception.AccessDeniedException;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final TokenService tokenService;

    @Autowired
    public UserService(UserRepository userRepository, @Lazy AuthenticationManager authenticationManager, UserMapper userMapper, TokenService tokenService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userMapper = userMapper;
    }

    public UserDTO login(LoginDTO login) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(login.email(), login.password());
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

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            System.out.println("No Users found");
            return List.of();
        }
        return users.stream().map(userMapper::toUserDTO).collect(Collectors.toList());
    }

    public UserDTO getUserById(String idUser) {
        User user = userRepository.findById(idUser).orElse(null);

        return userMapper.toUserDTO(user);
    }

    public void deleteUserById(String idUser) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userRepository.delete(user);
    }

    @Transactional // Garante operação atômica
    public UserDTO updateUser(String idUser, UserDTO updateUserDTO, String username) {
        User userToUpdate = validatePermission(idUser, username);

        if (updateUserDTO.getUsername() != null && !updateUserDTO.getUsername().isBlank()) {
            // Se o username mudou, verificar se o novo já não está em uso por outra pessoa
            if (!updateUserDTO.getUsername().equals(userToUpdate.getUsername()) && userRepository.existsUserByUsername(updateUserDTO.getUsername())) {
                throw new UserAlreadyExistsException("Conflict: Username already exists");
            }
            userToUpdate.setUsername(updateUserDTO.getUsername());
        }

        if (updateUserDTO.getBiography() != null) {
            userToUpdate.setBiography(updateUserDTO.getBiography());
        }

        if (updateUserDTO.getProfileImageUrl() != null) {
            userToUpdate.setProfileImageUrl(updateUserDTO.getProfileImageUrl());
        }

        User userUpdated = userRepository.save(userToUpdate);

        return userMapper.toUserDTO(userUpdated);
    }

    public User validatePermission(String idUser, String username) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if(!user.getUsername().equals(username)) {
            throw new AccessDeniedException("Access denied. You do not have permission to update this user");
        }
        return user;
    }


}
