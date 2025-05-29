package com.projeto_web.AllCritics.controller;

import com.projeto_web.AllCritics.dominio.enums.TipoOrdenacao;
import com.projeto_web.AllCritics.dto.ReviewDTO;
import com.projeto_web.AllCritics.repository.ReviewRepository;
import com.projeto_web.AllCritics.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;

    @Autowired
    private ReviewController(ReviewService reviewService, ReviewRepository reviewRepository) {
        this.reviewService = reviewService;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> buscaReviews(@RequestParam(required = false) TipoOrdenacao tipoOrdenacao) {
        List<ReviewDTO> reviews = reviewService.buscaReviews(tipoOrdenacao);
        return ResponseEntity.ok().body(reviews);
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> criaReview(@RequestBody ReviewDTO reviewDTO) {
        System.out.println("Início do review: " + reviewDTO);
        return ResponseEntity.ok().body(reviewService.criaReview(reviewDTO));
    }

    @DeleteMapping(value = "/{idReview}")
    public ResponseEntity<ReviewDTO> excluirReview(@PathVariable Long idReview) {
        reviewService.excluiReview(reviewService.buscaReviewPorId(idReview));
        return ResponseEntity.noContent().build();
    }

}
