package com.allcritics.api.domain.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ADMIN"), DEFAULT("DEFAULT");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

}
