package com.projeto_web.AllCritics.controller;

import com.projeto_web.AllCritics.dto.LoginDTO;
import com.projeto_web.AllCritics.dto.UsuarioDTO;
import com.projeto_web.AllCritics.pattern.factory.UsuarioFactory;
import com.projeto_web.AllCritics.service.TokenService;
import com.projeto_web.AllCritics.service.UsuarioService;
import com.projeto_web.AllCritics.validacao.UsuarioMensagemValidacao;
import com.projeto_web.AllCritics.validacao.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173") // Permite o frontend acessar esse endpoint
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final UsuarioFactory usuarioFactory;
    private final TokenService tokenService; // Para gerar JWT (opcional)
    @Autowired
    public AuthController(UsuarioService usuarioService, UsuarioFactory usuarioFactory, TokenService tokenService) {
        this.usuarioService = usuarioService;
        this.usuarioFactory = usuarioFactory;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody LoginDTO loginDTO) {
        if (loginDTO == null){
            throw new ValidacaoException(UsuarioMensagemValidacao.ERRO_USUARIO_LOGIN_BODY_VAZIO.getMensagem());
        }
        UsuarioDTO usuario = usuarioService.validaAcessoUsuario(loginDTO);

        // Gera um token JWT
        String token = tokenService.generateToken(usuario.getLogin());
        usuario.getLogin().setToken(token);

        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> registrar(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuario = usuarioFactory.transformaEmUsuarioDTO(usuarioService.cadastraUsuario(usuarioDTO));

        // Gera um token JWT
        String token = tokenService.generateToken(usuario.getLogin());
        usuario.getLogin().setToken(token);

        return ResponseEntity.ok().body(usuario);
    }
}
