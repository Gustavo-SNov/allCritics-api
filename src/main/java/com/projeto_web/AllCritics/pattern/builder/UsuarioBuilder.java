package com.projeto_web.AllCritics.pattern.builder;

import com.projeto_web.AllCritics.dominio.Login;
import com.projeto_web.AllCritics.dominio.Review;
import com.projeto_web.AllCritics.dominio.Usuario;

import java.time.LocalDate;
import java.util.List;


public class UsuarioBuilder {

    private Usuario usuario;

    private UsuarioBuilder() {
        this.usuario = new Usuario();
    }

    public static UsuarioBuilder getInstance() {
        return new UsuarioBuilder();
    }

    public UsuarioBuilder idUsuario(Long idUsuario) {
        this.usuario.setIdUsuario(idUsuario);
        return this;
    }

    public UsuarioBuilder nome(String nome) {
        this.usuario.setNome(nome);
        return this;
    }

    public UsuarioBuilder nomeIdentificador(String nomeIdentificador) {
        this.usuario.setNomeIdentificador(nomeIdentificador);
        return this;
    }

    public UsuarioBuilder biografia(String biografia) {
        this.usuario.setBiografia(biografia);
        return this;
    }

    public UsuarioBuilder urlFotoPerfil(String urlFotoPerfil) {
        this.usuario.setUrlFotoPerfil(urlFotoPerfil);
        return this;
    }

    public UsuarioBuilder dataCriacao(LocalDate dataCriacao) {
        this.usuario.setDataCriacao(dataCriacao);
        return this;
    }

    public UsuarioBuilder dataModificacao(LocalDate dataModificacao) {
        this.usuario.setDataModificacao(dataModificacao);
        return this;
    }

    public UsuarioBuilder login(Login login) {
        this.usuario.setLogin(login);
        return this;
    }


    public Usuario build() {
        return this.usuario;
    }

}
