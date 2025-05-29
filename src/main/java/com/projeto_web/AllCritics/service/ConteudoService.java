package com.projeto_web.AllCritics.service;

import com.projeto_web.AllCritics.dominio.Conteudo;
import com.projeto_web.AllCritics.dominio.enums.TipoConteudo;
import com.projeto_web.AllCritics.dominio.enums.TipoOrdenacao;
import com.projeto_web.AllCritics.dto.ConteudoDTO;
import com.projeto_web.AllCritics.dto.ReviewDTO;
import com.projeto_web.AllCritics.pattern.factory.ConteudoFactory;
import com.projeto_web.AllCritics.pattern.factory.ReviewFactory;
import com.projeto_web.AllCritics.repository.ConteudoRepository;
import com.projeto_web.AllCritics.repository.FilmeRepository;
import com.projeto_web.AllCritics.repository.JogoRepository;
import com.projeto_web.AllCritics.repository.SerieRepository;
import com.projeto_web.AllCritics.validacao.ConteudoExceptionHandler;
import com.projeto_web.AllCritics.validacao.ConteudoMensagemValidacao;
import com.projeto_web.AllCritics.validacao.ReviewExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConteudoService {

    private final ConteudoRepository conteudoRepository;
    private final FilmeRepository filmeRepository;
    private final JogoRepository jogoRepository;
    private final SerieRepository serieRepository;
    private final ConteudoFactory conteudoFactory;
    private final ReviewFactory reviewFactory;

    @Autowired
    private ConteudoService(ConteudoRepository conteudoRepository, FilmeRepository filmeRepository, JogoRepository jogoRepository, SerieRepository serieRepository, ConteudoFactory conteudoFactory, ReviewFactory reviewFactory) {
        this.conteudoRepository = conteudoRepository;
        this.filmeRepository = filmeRepository;
        this.jogoRepository = jogoRepository;
        this.serieRepository = serieRepository;
        this.conteudoFactory = conteudoFactory;
        this.reviewFactory = reviewFactory;
    }

    public List<? extends Conteudo> buscaConteudos(TipoConteudo tipoConteudo){
        tipoConteudo = Optional.ofNullable(tipoConteudo).orElse(TipoConteudo.ALL);

        return switch (tipoConteudo){
            case TipoConteudo.FILME -> filmeRepository.findAll();
            case TipoConteudo.JOGO -> jogoRepository.findAll();
            case TipoConteudo.SERIE -> serieRepository.findAll();
            case TipoConteudo.ALL -> conteudoRepository.findAll();
        };

    }

    public List<ConteudoDTO> ordenaConteudo(TipoOrdenacao tipoOrdenacao, List<? extends Conteudo> conteudos){

        tipoOrdenacao = Optional.ofNullable(tipoOrdenacao).orElse(TipoOrdenacao.NO_ORDER);
        List<? extends Conteudo> conteudosOrdenados = new ArrayList<>(conteudos);


        List<ConteudoDTO> novaLista = conteudosOrdenados.stream()
                .map(conteudoFactory::transformaEmConteudoDTO)
                .toList();

        return novaLista;
    }

    public ConteudoDTO buscaConteudoPorId(Long idConteudo){
        Conteudo conteudo = conteudoRepository.findById(idConteudo).orElseThrow(() -> new ReviewExceptionHandler(ConteudoMensagemValidacao.ERRO_CONTEUDO_NAO_ENCONTRADO.getMensagem()));

        ConteudoDTO conteudoDTO = conteudoFactory.transformaEmConteudoDTO(conteudo);

        conteudoDTO.setReviews(conteudo.getReviews().stream().map(reviewFactory::transformaEmReviewDTO).collect(Collectors.toList()));
        conteudoDTO.setNota(conteudoDTO.getReviews().stream().mapToDouble(ReviewDTO::getNota).average().orElse(0));
        return conteudoDTO;
    }

    public ConteudoDTO atualizaNotaConteudo(Long idConteudo){
        System.out.println("Entrou:  idConteudo-> "+idConteudo);
        Conteudo conteudo = conteudoRepository.findById(idConteudo).orElseThrow(() -> new ReviewExceptionHandler(ConteudoMensagemValidacao.ERRO_CONTEUDO_NAO_ENCONTRADO.getMensagem()));

        conteudo = conteudoRepository.save(conteudo);
        if (conteudo == null){
            throw new ConteudoExceptionHandler(ConteudoMensagemValidacao.ERRO_CONTEUDO_NAO_FOI_CADASTRADO.getMensagem());
        }
        conteudoRepository.flush();
        return conteudoFactory.transformaEmConteudoDTO(conteudo);

    }


}
