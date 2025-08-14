package com.allcritics.api.repository;

import com.allcritics.api.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {

    @Query("SELECT AVG(IFNULL(r.rate, 0)) FROM Review r WHERE r.content.idContent = :idContent")
    Double calculateAverageRatingByContentId(@Param("idContent") Long idContent);

}
