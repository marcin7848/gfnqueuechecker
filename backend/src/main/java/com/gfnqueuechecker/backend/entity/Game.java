package com.gfnqueuechecker.backend.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "game")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Game {

    @Id
    @Column(name = "game_id")
    @SequenceGenerator(name = "game_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_seq")
    @NotNull
    private Long id;

    @Column(name = "app_id")
    private Long appId;

    @Column(name = "game_name")
    private String gameName;

    @Column(name = "cover_img")
    private String coverImg;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    private List<LastSearched> lastSearched;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    private List<CheckQueue> checkQueue;
}
