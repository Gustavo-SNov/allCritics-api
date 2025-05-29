package com.projeto_web.AllCritics.pattern.builder;


import com.projeto_web.AllCritics.dto.LoginDTO;
import com.projeto_web.AllCritics.dto.UsuarioDTO;

import java.time.LocalDate;

public class UsuarioDTOBuilder {

    private UsuarioDTO usuario;

    private UsuarioDTOBuilder() {
        this.usuario = new UsuarioDTO();
    }

    public static UsuarioDTOBuilder getInstance() {
        return new UsuarioDTOBuilder();
    }

    public UsuarioDTOBuilder idUsuarioDTO(Long idUsuarioDTO) {
        this.usuario.setIdUsuario(idUsuarioDTO);
        return this;
    }

    public UsuarioDTOBuilder nome(String nome) {
        this.usuario.setNome(nome);
        return this;
    }

    public UsuarioDTOBuilder nomeIdentificador(String nomeIdentificador) {
        this.usuario.setNomeIdentificador(nomeIdentificador);
        return this;
    }

    public UsuarioDTOBuilder biografia(String biografia) {
        this.usuario.setBiografia(biografia);
        return this;
    }

    public UsuarioDTOBuilder urlFotoPerfil(String urlFotoPerfil) {
        this.usuario.setUrlFotoPerfil(urlFotoPerfil);
        return this;
    }

    public UsuarioDTOBuilder login(LoginDTO login) {
        this.usuario.setLogin(login);
        return this;
    }

    public UsuarioDTOBuilder dataCriacao(LocalDate dataCriacao) {
        this.usuario.setDataCriacao(dataCriacao);
        return this;
    }

    public UsuarioDTOBuilder dataModificacao(LocalDate dataModificacao) {
        this.usuario.setDataModificacao(dataModificacao);
        return this;
    }


    public UsuarioDTO build() {
        return this.usuario;
    }
}
