package com.allcritics.api.service;

import com.allcritics.api.domain.entity.User;
import com.allcritics.api.dto.user.UserDTO;
import com.allcritics.api.exception.user.UserAlreadyExistsException;
import com.allcritics.api.pattern.mapper.UserMapper;
import com.allcritics.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthService authService;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, @Lazy AuthService authService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.authService = authService;
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

    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        return userMapper.toUserDTO(user);
    }

    public void deleteUserById(String idUser) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userRepository.delete(user);
    }

    @Transactional // Garante operação atômica
    public UserDTO updateUser(String idUser, UserDTO updateUserDTO, String username) {
        User userToUpdate = authService.validatePermission(idUser, username);

        if (updateUserDTO.getUsername() != null && !updateUserDTO.getUsername().isBlank()) {
            // Se o username mudou, verificar se o novo já não está em uso por outra pessoa
            if (!updateUserDTO.getUsername().equals(userToUpdate.getUsername()) && userRepository.existsUserByUsername(updateUserDTO.getUsername())) {
                throw new UserAlreadyExistsException("Conflict: Username already exists");
            }
            userToUpdate.setUsername(updateUserDTO.getUsername());
        }

        if (updateUserDTO.getAccountName() != null && !updateUserDTO.getAccountName().isBlank()) {
            userToUpdate.setAccountName(updateUserDTO.getAccountName());
        }

        if (updateUserDTO.getBiography() != null) {
            userToUpdate.setBiography(updateUserDTO.getBiography());
        }

        if (updateUserDTO.getProfileImgUrl() != null) {
            userToUpdate.setProfileImgUrl(updateUserDTO.getProfileImgUrl());
        }

        if (updateUserDTO.getCoverImgUrl() != null) {
            userToUpdate.setCoverImgUrl(updateUserDTO.getCoverImgUrl());
        }

        User userUpdated = userRepository.save(userToUpdate);

        return userMapper.toUserDTO(userUpdated);
    }

}
