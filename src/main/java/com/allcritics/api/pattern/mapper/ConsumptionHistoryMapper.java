package com.allcritics.api.pattern.mapper;

import com.allcritics.api.domain.entity.ConsumptionHistory;
import com.allcritics.api.dto.consumptionHistory.ConsumptionHistoryCreateDTO;
import com.allcritics.api.dto.consumptionHistory.ConsumptionHistoryDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ConsumptionHistoryMapper {

    @Autowired
    @Lazy
    private ContentMapper contentMapper;

    public ConsumptionHistoryDTO toConsumptionHistoryDTO(ConsumptionHistory consumptionHistory) {
        if(consumptionHistory == null) {
            return null;
        }

        return ConsumptionHistoryDTO.builder()
                .idConsumptionHistory(consumptionHistory.getIdConsumptionHistory())
                .consumptionDate(consumptionHistory.getConsumptionDate())
                .contentDTO(contentMapper.toContentDTO(consumptionHistory.getContent())).build();
    }

    public ConsumptionHistory toCreateConsumptionHistory(ConsumptionHistoryCreateDTO consumptionHistoryCreateDTO) {
        if (consumptionHistoryCreateDTO == null) return null;

        return ConsumptionHistory.builder()
                .build();
    }
}
