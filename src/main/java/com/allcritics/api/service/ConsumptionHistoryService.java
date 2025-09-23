package com.allcritics.api.service;

import com.allcritics.api.domain.entity.ConsumptionHistory;
import com.allcritics.api.domain.entity.Content;
import com.allcritics.api.domain.entity.User;
import com.allcritics.api.dto.consumptionHistory.ConsumptionHistoryCreateDTO;
import com.allcritics.api.dto.consumptionHistory.ConsumptionHistoryDTO;
import com.allcritics.api.dto.consumptionHistory.ConsumptionHistoryFilter;
import com.allcritics.api.pattern.mapper.ConsumptionHistoryMapper;
import com.allcritics.api.repository.ConsumptionHistoryRepository;
import com.allcritics.api.repository.ContentRepository;
import com.allcritics.api.repository.UserRepository;
import com.allcritics.api.repository.specification.ConsumptionHistorySpecification;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConsumptionHistoryService {

    private final ConsumptionHistoryRepository consumptionHistoryRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;
    private final ConsumptionHistoryMapper consumptionHistoryMapper;
    private final ConsumptionHistorySpecification consumptionHistorySpecification;

    public ConsumptionHistory getHistoryById(Long historyId) {
        return consumptionHistoryRepository.findById(historyId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<ConsumptionHistoryDTO> getHistoryByUser(ConsumptionHistoryFilter consumptionHistoryFilter) {
        Specification<ConsumptionHistory> specification = consumptionHistorySpecification.getFilterToConsumptionHistory(consumptionHistoryFilter);
        List<ConsumptionHistory> history = consumptionHistoryRepository.findAll(specification);
        return history.stream().map(consumptionHistoryMapper::toConsumptionHistoryDTO).collect(Collectors.toList());
    }

    @Transactional
    public ConsumptionHistoryDTO createHistory(@Valid ConsumptionHistoryCreateDTO consumptionHistoryCreateDTO) {
        ConsumptionHistory consumptionHistory = consumptionHistoryMapper.toCreateConsumptionHistory(consumptionHistoryCreateDTO);

        Content contentRef = contentRepository.getReferenceById(consumptionHistoryCreateDTO.idContent());
        User userRef = userRepository.getReferenceById(consumptionHistoryCreateDTO.idUser());

        consumptionHistory.setContent(contentRef);
        consumptionHistory.setUser(userRef);

        ConsumptionHistory savedConsumptionHistory = consumptionHistoryRepository.save(consumptionHistory);

        return consumptionHistoryMapper.toConsumptionHistoryDTO(savedConsumptionHistory);
    }

    @Transactional
    public ConsumptionHistoryDTO updateHistory(Long historyId, ConsumptionHistoryCreateDTO consumptionHistoryCreateDTO) {
        ConsumptionHistory consumptionHistory = this.getHistoryById(historyId);

        consumptionHistory.setConsumptionDate(LocalDateTime.now());

        return consumptionHistoryMapper.toConsumptionHistoryDTO(consumptionHistoryRepository.save(consumptionHistory));
    }

    @Transactional
    public void upsertHistory(@Valid ConsumptionHistoryCreateDTO dto) {
        Long idContent = dto.idContent();
        String idUser = dto.idUser();

        Optional<ConsumptionHistory> existingOpt =
                consumptionHistoryRepository.findByContent_IdContentAndUser_IdUser(idContent, idUser);

        if (existingOpt.isPresent()) {
            ConsumptionHistory existing = existingOpt.get();
            existing.setConsumptionDate(LocalDateTime.now());
            consumptionHistoryRepository.save(existing);
        } else {
            ConsumptionHistory toSave = consumptionHistoryMapper.toCreateConsumptionHistory(dto);
            Content contentRef = contentRepository.getReferenceById(idContent);
            User userRef = userRepository.getReferenceById(idUser);
            toSave.setContent(contentRef);
            toSave.setUser(userRef);
            consumptionHistoryRepository.save(toSave);
        }
    }

    @Transactional
    public void deleteHistoryById(Long historyId) {
        consumptionHistoryRepository.delete(this.getHistoryById(historyId));
    }

}
