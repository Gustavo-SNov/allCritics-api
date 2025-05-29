package com.projeto_web.AllCritics.service;

import com.projeto_web.AllCritics.dominio.Login;
import com.projeto_web.AllCritics.dominio.Usuario;
import com.projeto_web.AllCritics.dto.LoginDTO;
import com.projeto_web.AllCritics.dto.UsuarioDTO;
import com.projeto_web.AllCritics.pattern.factory.ConteudoFactory;
import com.projeto_web.AllCritics.pattern.factory.ReviewFactory;
import com.projeto_web.AllCritics.pattern.factory.UsuarioFactory;
import com.projeto_web.AllCritics.repository.LoginRepository;
import com.projeto_web.AllCritics.repository.UsuarioRepository;
import com.projeto_web.AllCritics.validacao.UsuarioExceptionHandler;
import com.projeto_web.AllCritics.validacao.UsuarioMensagemValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioFactory usuarioFactory;
    @Lazy
    private final ConteudoFactory conteudoFactory;
    @Lazy
    private final ReviewFactory reviewFactory;
    private final UsuarioRepository usuarioRepository;
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioFactory usuarioFactory, LoginRepository loginRepository, ReviewFactory reviewFactory, ConteudoFactory conteudoFactory) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioFactory = usuarioFactory;
        this.loginRepository = loginRepository;
        this.reviewFactory = reviewFactory;
        this.conteudoFactory = conteudoFactory;
    }

    public UsuarioDTO validaAcessoUsuario(LoginDTO loginDTO) {
        // 1. VALIDA SE O EMAIL CORRESPONDE A UM USUÁRIO CADASTRADO
        System.out.println("LoginDTO: " + loginDTO);
        Login login = loginRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new UsuarioExceptionHandler(UsuarioMensagemValidacao.ERRO_USUARIO_VALIDACAO_EMAIL.getMensagem()));

        // 2. VALIDA SENHA DIGITADA E SENHA ARMAZENADA
        if (!verificarSenha(loginDTO.getSenha(), login.getSenha())) {
            throw new UsuarioExceptionHandler(UsuarioMensagemValidacao.ERRO_USUARIO_VALIDACAO_SENHA.getMensagem());
        }
        return this.buscaUsuarioPorId(login.getIdUsuario());
    }

    public boolean verificarSenha(String senhaDigitada, String senhaArmazenada) {
        return passwordEncoder.matches(senhaDigitada, senhaArmazenada);
    }

    public List<Usuario> buscaUsuarios() {
        return usuarioRepository.findAll();
    }


    public UsuarioDTO buscaUsuarioPorId(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);

        if (usuario == null) {
            throw new UsuarioExceptionHandler(UsuarioMensagemValidacao.ERRO_USUARIO_NAO_ENCONTRADO.getMensagem());
        }
        UsuarioDTO usuarioDTO = usuarioFactory.transformaEmUsuarioDTO(usuario);
        usuarioDTO.setReviews(usuario.getReviews().stream().map(reviewFactory::transformaEmReviewDTO).collect(Collectors.toList()));
        usuarioDTO.setConteudos(usuario.getReviews().stream().map(review -> conteudoFactory.transformaEmConteudoDTO(review.getConteudo())).distinct().collect(Collectors.toList()));
        return usuarioDTO;
    }

    public Usuario cadastraUsuario(UsuarioDTO usuarioDTO) {
        validaUsuario(usuarioDTO);
        Usuario usuario = usuarioFactory.transformaEmUsuario(usuarioDTO);

        // Salva o usuário primeiro para garantir que tenha um ID
        usuario = usuarioRepository.save(usuario);
        usuarioRepository.flush(); // Garante que o ID foi gerado no banco


        if (usuario.getLogin() != null) {
            usuario.getLogin().setUsuario(usuario);
            usuario.getLogin().setIdUsuario(usuario.getIdUsuario());
            loginRepository.save(usuario.getLogin());
        }

        if (usuario == null) {
            throw new UsuarioExceptionHandler(UsuarioMensagemValidacao.ERRO_USUARIO_NAO_FOI_CADASTRADO.getMensagem());
        }

        return usuario;
    }

    public void validaUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getIdUsuario() != null) {
            boolean usuarioNaoExiste = usuarioRepository.findById(usuarioDTO.getIdUsuario()).orElse(null) == null;
            if (usuarioNaoExiste) {
                throw new UsuarioExceptionHandler(UsuarioMensagemValidacao.ERRO_USUARIO_NAO_ENCONTRADO.getMensagem());
            }
        }
        if (usuarioDTO.getNome() == null || usuarioDTO.getNome().length() < 3) {
            throw new UsuarioExceptionHandler(UsuarioMensagemValidacao.ERRO_USUARIO_NOME_INVALIDO.getMensagem());
        }

        if (usuarioDTO.getLogin().getEmail() == null || usuarioDTO.getLogin().getSenha() == null) {
            throw new UsuarioExceptionHandler(UsuarioMensagemValidacao.ERRO_USUARIO_EMAIL_SENHA_OBRIGATORIOS.getMensagem());
        }

        boolean emailJaExiste = loginRepository.findByEmail(usuarioDTO.getLogin().getEmail()).isPresent();
        if (emailJaExiste && usuarioDTO.getIdUsuario() == null) {
            throw new UsuarioExceptionHandler(UsuarioMensagemValidacao.ERRO_USUARIO_EMAIL_JA_CADASTRADO.getMensagem());
        }

        if (usuarioDTO.getDataCriacao() == null) {
            usuarioDTO.setDataCriacao(LocalDate.now());
        }
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void excluir(Usuario usuario) {
        if (usuario == null) {
            throw new UsuarioExceptionHandler(UsuarioMensagemValidacao.ERRO_USUARIO_NAO_ENCONTRADO_AO_EXCLUIR.getMensagem());
        }

        usuarioRepository.deleteById(usuario.getIdUsuario());
        usuarioRepository.flush();
        boolean usuarioExiste = usuarioRepository.findById(usuario.getIdUsuario()).orElse(null) != null;
        if (usuarioExiste) {
            throw new UsuarioExceptionHandler(UsuarioMensagemValidacao.ERRO_USUARIO_AINDA_EXISTE.getMensagem());
        }
    }
}
