package com.allcritics.api.repository;

import com.allcritics.api.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Boolean existsUserByEmail(String email);
    Boolean existsUserByUsername(String username);
    Optional<User> findByUsername(String username);
}
