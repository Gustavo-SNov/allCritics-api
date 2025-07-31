package com.allcritics.api.repository;

import com.allcritics.api.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    Boolean existsUserByEmail(String email);
    Boolean existsUserByUsername(String username);
}
