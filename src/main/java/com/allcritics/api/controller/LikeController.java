package com.allcritics.api.controller;

import com.allcritics.api.dto.like.LikeDTO;
import com.allcritics.api.dto.like.LikeRequest;
import com.allcritics.api.service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<LikeDTO> likeReview(@Valid @RequestBody LikeRequest likeRequest, Authentication authentication) {
        LikeDTO like = likeService.createLike(likeRequest, authentication);
        return ResponseEntity.ok().body(like);
    }

    @DeleteMapping(value = "/{idLike}")
    public ResponseEntity<Void> unlikeReview(@PathVariable Long idLike, Authentication authentication) {
        likeService.deleteLike(idLike, authentication);
        return ResponseEntity.noContent().build();
    }

}
