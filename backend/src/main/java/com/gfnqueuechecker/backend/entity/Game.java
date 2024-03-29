package com.gfnqueuechecker.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "game_name", length = 500)
    private String gameName;

    @Column(name = "cover_img", length = 2000)
    private String coverImg;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    @JsonIgnore
    private List<LastSearched> lastSearched;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    @JsonIgnore
    private List<CheckQueue> checkQueue;
}
