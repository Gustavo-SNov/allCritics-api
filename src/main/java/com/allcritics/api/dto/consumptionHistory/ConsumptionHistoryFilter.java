package com.allcritics.api.dto.consumptionHistory;


import java.time.LocalDateTime;

public record ConsumptionHistoryFilter(
        LocalDateTime startDate,
        LocalDateTime endDate,
        Long idContent,
        String idUser
) {
    public ConsumptionHistoryFilter(ConsumptionHistoryFilter consumptionHistoryFilter, String idUser) {
        this(
                consumptionHistoryFilter.startDate(),
                consumptionHistoryFilter.endDate(),
                consumptionHistoryFilter.idContent(),
                idUser
        );
    }
}
