package com.projeto_web.AllCritics.dto;

import lombok.Getter;
import lombok.Setter;


public class LoginDTO {

    private String email;
    private String senha;
    private String token;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
