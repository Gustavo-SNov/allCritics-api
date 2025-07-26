package com.allcritics.api.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "serie")
@PrimaryKeyJoinColumn(name = "idContent")
public class Serie extends Content{

    @Column(name = "number_Seasons")
    private Long numberOfSeasons;
    @Column(name = "episodes_Season")
    private Long episodesPerSeason;
    @Column(name = "broadcaster")
    private String broadcaster;

    @Override
    public String toString() {
        return "Serie{" +
                "numberOfSeasons=" + numberOfSeasons +
                ", episodesPerSeason=" + episodesPerSeason +
                ", broadcaster='" + broadcaster + '\'' +
                '}';
    }
}
