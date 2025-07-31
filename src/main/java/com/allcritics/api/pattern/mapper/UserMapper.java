package com.allcritics.api.pattern.mapper;

import com.allcritics.api.domain.entity.User;
import com.allcritics.api.dto.user.RegisterDTO;
import com.allcritics.api.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User toRegisterUser(RegisterDTO registerDTO) {
        if (registerDTO == null) {
            return null;
        }

        User.UserBuilder user = User.builder()
                .idUser(UUID.randomUUID().toString())
                .username(registerDTO.username())
                .email(registerDTO.email())
                .password(passwordEncoder.encode(registerDTO.password()))
                .createDate(LocalDate.now());
        return user.build();
    }

    public UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTOBuilder = UserDTO.builder()
                .idUser(user.getIdUser())
                .username(user.getUsername())
                .email(user.getEmail())
                .biography(user.getBiography())
                .profileImageUrl(user.getProfileImageUrl())
                .createDate(user.getCreateDate())
                .updateDate(user.getUpdateDate());

        return userDTOBuilder.build();
    }
}
