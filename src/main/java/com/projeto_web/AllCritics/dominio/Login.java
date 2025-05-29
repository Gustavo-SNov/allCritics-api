package com.projeto_web.AllCritics.dominio;

import jakarta.persistence.*;



@Entity
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_login")
    private Long idLogin;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "senha", nullable = false)
    private String senha;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, insertable = false, updatable = false)
    private Usuario usuario;

    public Long getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Long idLogin) {
        this.idLogin = idLogin;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Login{" +
                "idLogin=" + idLogin +
                ", idUsuario=" + idUsuario +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
