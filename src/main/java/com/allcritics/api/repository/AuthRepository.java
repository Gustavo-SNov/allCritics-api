package com.allcritics.api.repository;

import com.allcritics.api.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthRepository extends JpaRepository<User, String> {
    UserDetails findByEmail(String email);
    UserDetails findByUsername(String username);
}
