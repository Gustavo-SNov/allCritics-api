package com.projeto_web.AllCritics.repository;

import com.projeto_web.AllCritics.dominio.Review;
import com.projeto_web.AllCritics.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


}
