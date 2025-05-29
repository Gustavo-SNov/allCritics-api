package com.projeto_web.AllCritics.pattern.factory;

import com.projeto_web.AllCritics.dominio.Login;
import com.projeto_web.AllCritics.dominio.Usuario;
import com.projeto_web.AllCritics.dto.LoginDTO;
import com.projeto_web.AllCritics.dto.UsuarioDTO;
import com.projeto_web.AllCritics.pattern.builder.UsuarioBuilder;
import com.projeto_web.AllCritics.pattern.builder.UsuarioDTOBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioFactory {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioFactory( PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario transformaEmUsuario(UsuarioDTO usuarioDTO) {
        return UsuarioBuilder.getInstance()
                .idUsuario(usuarioDTO.getIdUsuario()).nome(usuarioDTO.getNome())
                .nomeIdentificador(usuarioDTO.getNomeIdentificador()).biografia(usuarioDTO.getBiografia())
                .urlFotoPerfil(usuarioDTO.getUrlFotoPerfil()).dataCriacao(usuarioDTO.getDataCriacao()).dataModificacao(usuarioDTO.getDataModificacao())
                .login(this.transformaEmLogin(usuarioDTO.getLogin()))
                .build();
    }

    public UsuarioDTO transformaEmUsuarioDTO(Usuario usuario){
        if (usuario == null){
            return null;
        }
        return UsuarioDTOBuilder.getInstance()
                .idUsuarioDTO(usuario.getIdUsuario()).nome(usuario.getNome()).nomeIdentificador(usuario.getNomeIdentificador())
                .biografia(usuario.getBiografia()).urlFotoPerfil(usuario.getUrlFotoPerfil())
                .dataCriacao(usuario.getDataCriacao()).dataModificacao(usuario.getDataModificacao())
                .login(this.transformaEmLoginDTO(usuario.getLogin()))
                .build();
    }

    public LoginDTO transformaEmLoginDTO(Login login){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(login.getEmail());
        loginDTO.setSenha(login.getSenha());
        return loginDTO;
    }

    public Login transformaEmLogin(LoginDTO loginDTO){
        Login login = new Login();
        login.setEmail(loginDTO.getEmail());
        login.setSenha(passwordEncoder.encode(loginDTO.getSenha()));
        return login;
    }
}
