package com.allcritics.api.dto.consumptionHistory;

import jakarta.validation.constraints.NotNull;

public record ConsumptionHistoryCreateDTO(
        @NotNull(message = "Content ID must be informed") Long idContent,
        String idUser
        ) {
}
