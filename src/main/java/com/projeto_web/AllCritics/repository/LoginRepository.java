package com.projeto_web.AllCritics.repository;

import com.projeto_web.AllCritics.dominio.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long> {
     Optional<Login> findByEmail(String email);
}
