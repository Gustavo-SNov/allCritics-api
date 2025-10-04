package com.allcritics.api.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "consumption_history")
public class ConsumptionHistory {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id_consumption_history")
    private Long idConsumptionHistory;

    @Column(name = "consumption_date")
    private LocalDateTime consumptionDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_content", nullable = false)
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @PrePersist
    public void prePersist() {
        if (this.consumptionDate == null) {
            this.consumptionDate = LocalDateTime.now();
        }
    }

}
