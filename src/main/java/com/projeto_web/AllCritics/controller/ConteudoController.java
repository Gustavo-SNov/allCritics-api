package com.projeto_web.AllCritics.controller;

import com.projeto_web.AllCritics.dominio.Conteudo;
import com.projeto_web.AllCritics.dominio.enums.TipoConteudo;
import com.projeto_web.AllCritics.dominio.enums.TipoOrdenacao;
import com.projeto_web.AllCritics.dto.ConteudoDTO;
import com.projeto_web.AllCritics.service.ConteudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conteudo")
public class ConteudoController {

    private final ConteudoService conteudoService;

    @Autowired
    private ConteudoController(ConteudoService conteudoService) {
        this.conteudoService = conteudoService;
    }

    @GetMapping
    public ResponseEntity<List<ConteudoDTO>> buscaConteudos(@RequestParam(required = false) TipoConteudo tipoConteudo,
                                                            @RequestParam(required = false) TipoOrdenacao tipoOrdenacao) {
        List<? extends Conteudo> conteudos =  conteudoService.buscaConteudos(tipoConteudo);

        List<ConteudoDTO> conteudosOrdenados = conteudoService.ordenaConteudo(tipoOrdenacao,conteudos);
        return ResponseEntity.ok().body(conteudosOrdenados);
    }

    @GetMapping(value ="/{idConteudo}" )
    public ResponseEntity<ConteudoDTO> buscaConteudoPorId(@PathVariable Long idConteudo) {
        ConteudoDTO conteudo = conteudoService.buscaConteudoPorId(idConteudo);
        return ResponseEntity.ok().body(conteudo);
    }

}

