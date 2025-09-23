package com.allcritics.api.repository;

import com.allcritics.api.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {

    @Query(value= """
            WITH content_stats AS (
              SELECT
                r.id_content,
                AVG(r.rate)   AS R,
                COUNT(r.rate) AS v
              FROM review r
              GROUP BY r.id_content
            ),
            global_avg AS (
              SELECT AVG(rate) AS C
              FROM review
            )
            SELECT
              ROUND(
                (
                  (cs.v * 1.0 / (cs.v + 5)) * cs.R
                  + (5 * 1.0 / (cs.v + 5)) * ga.C
                )
              , 2) AS weighted_rating
            FROM content_stats cs
            CROSS JOIN global_avg ga
            WHERE cs.v >= 5
            ORDER BY weighted_rating DESC;
            """, nativeQuery = true)
    Double calculateAverageRatingByContentId(@Param("idContent") Long idContent);

}
