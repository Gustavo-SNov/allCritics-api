package com.allcritics.api.dto.user;

import com.allcritics.api.domain.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    // --- Dados de Identidade/Conta ---
    private String idUser;
    private String username;
    private String email;
    private String token;
    private UserRole role;

    // --- Dados de Perfil ---
    private String biography;
    private String profileImageUrl;
    private LocalDate createDate;
    private LocalDate updateDate;
}
