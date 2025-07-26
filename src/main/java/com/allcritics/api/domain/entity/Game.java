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
@Table(name = "game")
@PrimaryKeyJoinColumn(name = "idContent")
public class Game extends Content{

    @Column(name = "studio")
    private String studio;

    @Column(name = "platform")
    private String platform;

    @Column(name = "multiplayer")
    private Boolean multiplayer;

    @Override
    public String toString() {
        return "Game{" +
                "studio='" + studio + '\'' +
                ", platform='" + platform + '\'' +
                ", multiplayer=" + multiplayer +
                '}';
    }
}
