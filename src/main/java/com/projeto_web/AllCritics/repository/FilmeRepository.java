package com.projeto_web.AllCritics.repository;

import com.projeto_web.AllCritics.dominio.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
