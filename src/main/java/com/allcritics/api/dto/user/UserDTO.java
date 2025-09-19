package com.allcritics.api.dto.user;

import com.allcritics.api.domain.enums.UserRole;
import com.allcritics.api.dto.review.ReviewDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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
    private String accountName;
    private String biography;
    private String profileImgUrl;
    private String coverImgUrl;
    private LocalDate createDate;
    private LocalDate updateDate;
    private List<ReviewDTO> reviews;

}
