package com.projeto_web.AllCritics.repository;

import com.projeto_web.AllCritics.dominio.Conteudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConteudoRepository extends JpaRepository<Conteudo, Long> {


    @Query("SELECT c FROM Conteudo c WHERE c.tipoConteudo = 'Filme'")
    List<Conteudo> buscaFilmes();
    @Query("SELECT c FROM Conteudo c WHERE c.tipoConteudo = 'Jogo'")
    List<Conteudo> buscaJogos();
    @Query("SELECT c FROM Conteudo c WHERE c.tipoConteudo = 'Série'")
    List<Conteudo> buscaSerie();
}
