package com.allcritics.api.dto.consumptionHistory;

import com.allcritics.api.domain.entity.Content;
import com.allcritics.api.dto.conteudo.ContentDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsumptionHistoryDTO {

    private Long idConsumptionHistory;

    private LocalDateTime consumptionDate;

    private ContentDTO contentDTO;

}
