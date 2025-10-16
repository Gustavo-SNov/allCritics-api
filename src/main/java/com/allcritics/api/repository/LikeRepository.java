package com.allcritics.api.repository;

import com.allcritics.api.domain.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {


}
