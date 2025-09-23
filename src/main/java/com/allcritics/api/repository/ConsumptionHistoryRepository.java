package com.allcritics.api.repository;

import com.allcritics.api.domain.entity.ConsumptionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ConsumptionHistoryRepository extends JpaRepository<ConsumptionHistory, Long>, JpaSpecificationExecutor<ConsumptionHistory> {
    Optional<ConsumptionHistory> findByContent_IdContentAndUser_IdUser(Long contentId, String userId);
}
