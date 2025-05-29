package com.projeto_web.AllCritics.repository;

import com.projeto_web.AllCritics.dominio.Conteudo;
import com.projeto_web.AllCritics.dominio.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r WHERE r.conteudo.idConteudo = :idConteudo")
    List<Review> buscaReviewsConteudo(@Param("idConteudo") Long idConteudo);
}
