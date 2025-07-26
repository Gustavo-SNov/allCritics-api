package com.allcritics.api.repository;

import com.allcritics.api.domain.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
