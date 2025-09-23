package com.allcritics.api.controller;

import com.allcritics.api.domain.entity.User;
import com.allcritics.api.dto.consumptionHistory.ConsumptionHistoryCreateDTO;
import com.allcritics.api.dto.consumptionHistory.ConsumptionHistoryDTO;
import com.allcritics.api.dto.consumptionHistory.ConsumptionHistoryFilter;
import com.allcritics.api.service.ConsumptionHistoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumption-history")
public class ConsumptionHistoryController {

    private final ConsumptionHistoryService consumptionHistoryService;

    @Autowired
    private ConsumptionHistoryController(ConsumptionHistoryService consumptionHistoryService) {
        this.consumptionHistoryService = consumptionHistoryService;
    }

    @GetMapping
    public ResponseEntity<List<ConsumptionHistoryDTO>> getHistoryByUser(
            ConsumptionHistoryFilter consumptionHistoryFilter,
            @AuthenticationPrincipal User loggedUser) {

        if (loggedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String idUser = loggedUser.getIdUser();

        ConsumptionHistoryFilter consumptionHistoryFilterWithUser = new ConsumptionHistoryFilter(consumptionHistoryFilter, idUser);

        List<ConsumptionHistoryDTO> history = consumptionHistoryService.getHistoryByUser(consumptionHistoryFilterWithUser);
        return ResponseEntity.ok().body(history);
    }

    @PostMapping
    public ResponseEntity<ConsumptionHistoryDTO> createHistory(
            @Valid @RequestBody ConsumptionHistoryCreateDTO consumptionHistoryCreateDTO,
            @AuthenticationPrincipal User loggedUser
    ) {
        if (loggedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String idUser = loggedUser.getIdUser();

        ConsumptionHistoryCreateDTO consumptionHistoryCreateDTOWithUser = new ConsumptionHistoryCreateDTO(
                consumptionHistoryCreateDTO.idContent(),
                idUser
        );

        ConsumptionHistoryDTO consumptionHistoryDTO = consumptionHistoryService.createHistory(consumptionHistoryCreateDTOWithUser);
        return ResponseEntity.ok().body(consumptionHistoryDTO);
    }

    @DeleteMapping(value = "/{idConsumptionHistory}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long idConsumptionHistory) {
        consumptionHistoryService.deleteHistoryById(idConsumptionHistory);
        return ResponseEntity.noContent().build();
    }

}
