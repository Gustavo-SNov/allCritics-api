package com.projeto_web.AllCritics.service;

import com.projeto_web.AllCritics.dominio.Review;
import com.projeto_web.AllCritics.dominio.enums.TipoOrdenacao;
import com.projeto_web.AllCritics.dto.ReviewDTO;
import com.projeto_web.AllCritics.pattern.factory.ConteudoFactory;
import com.projeto_web.AllCritics.pattern.factory.ReviewFactory;
import com.projeto_web.AllCritics.repository.ConteudoRepository;
import com.projeto_web.AllCritics.repository.ReviewRepository;
import com.projeto_web.AllCritics.validacao.ReviewExceptionHandler;
import com.projeto_web.AllCritics.validacao.ReviewMensagemValidacao;
import com.projeto_web.AllCritics.validacao.UsuarioExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewFactory reviewFactory;
    private final ConteudoRepository conteudoRepository;
    private final ConteudoService conteudoService;
    private final UsuarioService usuarioService;
    private final ConteudoFactory conteudoFactory;


    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ReviewFactory reviewFactory, ConteudoRepository conteudoRepository, ConteudoService conteudoService, UsuarioService usuarioService, ConteudoFactory conteudoFactory) {
        this.reviewRepository = reviewRepository;
        this.reviewFactory = reviewFactory;
        this.conteudoRepository = conteudoRepository;
        this.conteudoService = conteudoService;
        this.usuarioService = usuarioService;
        this.conteudoFactory = conteudoFactory;
    }

    public ReviewDTO buscaReviewPorId(Long idReview) {
        Review review = reviewRepository.findById(idReview).orElseThrow(()-> new ReviewExceptionHandler(ReviewMensagemValidacao.ERRO_REVIEW_NAO_ENCONTRADO.getMensagem()));

        return reviewFactory.transformaEmReviewDTO(review);
    }

    public List<ReviewDTO> buscaReviews(TipoOrdenacao tipoOrdenacao){
        tipoOrdenacao = Optional.ofNullable(tipoOrdenacao).orElse(TipoOrdenacao.NO_ORDER);
        List<Review> reviews = reviewRepository.findAll();

        if (tipoOrdenacao.equals(TipoOrdenacao.ORDER_BY_ULTIMOS)){
            List<Review> reviewsOrdenados = reviews.stream().sorted(Comparator.comparing(Review::getDataCriacao).reversed()).toList();
            return reviewsOrdenados.stream().map(reviewFactory::transformaEmReviewDTO).toList();
        }

        return reviews.stream().map(reviewFactory::transformaEmReviewDTO).toList();
    }

    public ReviewDTO criaReview(ReviewDTO reviewDTO){
        System.out.println("ReviewDTO: " + reviewDTO);
        System.out.println("idConteudo: " + reviewDTO.getIdConteudo());
        Review review = reviewRepository.save(reviewFactory.transformaEmReview(reviewDTO));
        reviewRepository.flush();

        if (review == null) {
            throw new ReviewExceptionHandler(ReviewMensagemValidacao.ERRO_REVIEW_NAO_FOI_CADASTRADO.getMensagem());
        }
        System.out.println("Review: " + review);
        reviewDTO = reviewFactory.transformaEmReviewDTO(review);
        reviewDTO.setConteudoDTO(conteudoService.atualizaNotaConteudo(review.getIdConteudo()));
        return reviewDTO;

    }

    public void excluiReview(ReviewDTO reviewDTO){
        reviewRepository.delete(reviewFactory.transformaEmReview(reviewDTO));
        reviewRepository.flush();
        reviewDTO = this.buscaReviewPorId(reviewDTO.getIdReview());
        if (reviewDTO.getIdReview() == null){
            throw new ReviewExceptionHandler(ReviewMensagemValidacao.ERRO_REVIEW_NAO_FOI_EXCLUIDO.getMensagem());
        }
    }

}
