package com.allcritics.api.pattern.mapper;

import com.allcritics.api.domain.entity.User;
import com.allcritics.api.domain.enums.UserRole;
import com.allcritics.api.dto.user.RegisterDTO;
import com.allcritics.api.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthMapper {

    private final PasswordEncoder passwordEncoder;

    public User toRegisterUser(RegisterDTO registerDTO) {
        if (registerDTO == null) {
            return null;
        }

        User.UserBuilder user = User.builder()
                .username(registerDTO.username())
                .email(registerDTO.email())
                .password(passwordEncoder.encode(registerDTO.password()))
                .role(UserRole.DEFAULT)
                .accountName(registerDTO.accountName())
                .createDate(LocalDate.now());
        return user.build();
    }

    public UserDTO toAutheticatedUser(User user, String token){
        if (user == null || token == null) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTOBuilder = UserDTO.builder()
                .idUser(user.getIdUser())
                .username(user.getUsername())
                .email(user.getEmail())
                .token(token)
                .role(Optional.ofNullable(user.getRole()).orElse(UserRole.DEFAULT))
                .accountName(user.getAccountName())
                .biography(user.getBiography())
                .profileImgUrl(user.getProfileImgUrl())
                .coverImgUrl(user.getCoverImgUrl())
                .createDate(user.getCreateDate())
                .updateDate(user.getUpdateDate());
        return userDTOBuilder.build();
    }
}
