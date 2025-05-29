package com.projeto_web.AllCritics.controller;

import com.projeto_web.AllCritics.dto.UsuarioDTO;
import com.projeto_web.AllCritics.pattern.factory.UsuarioFactory;
import com.projeto_web.AllCritics.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioFactory usuarioFactory;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, UsuarioFactory usuarioFactory) {
        this.usuarioService = usuarioService;
        this.usuarioFactory = usuarioFactory;
    }

    @GetMapping(value = "/{idUsuario}")
    public ResponseEntity<UsuarioDTO> buscaUsuario(@PathVariable Long idUsuario) {
        UsuarioDTO usuario = usuarioService.buscaUsuarioPorId(idUsuario);
        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscaUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.buscaUsuarios().stream().map(usuarioFactory::transformaEmUsuarioDTO).toList();
        return ResponseEntity.ok().body(usuarios);
    }

    @PutMapping(value = "/{idUsuario}")
    public ResponseEntity<UsuarioDTO> alteraUsuario(@RequestBody UsuarioDTO usuarioDTO,@PathVariable Long idUsuario) {
        usuarioDTO.setIdUsuario(idUsuario);
        UsuarioDTO usuarioAlterado = usuarioFactory.transformaEmUsuarioDTO(usuarioService.cadastraUsuario(usuarioDTO));
        return ResponseEntity.ok().body(usuarioAlterado);
    }

    @DeleteMapping(value = "/{idUsuario}")
    public ResponseEntity<UsuarioDTO> excluirUsuario(@PathVariable Long idUsuario) {
        UsuarioDTO usuarioDTO = usuarioService.buscaUsuarioPorId(idUsuario);
        usuarioService.excluir(usuarioFactory.transformaEmUsuario(usuarioDTO));
        return ResponseEntity.noContent().build();
    }

}
