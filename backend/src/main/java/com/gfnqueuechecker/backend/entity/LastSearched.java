package com.gfnqueuechecker.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

@Component
@Entity
@Table(name = "last_searched")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class LastSearched {

    @Id
    @Column(name = "last_searched_id")
    @SequenceGenerator(name = "last_searched_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "last_searched_seq")
    @NotNull
    private Long id;

    @Column(name = "search_time")
    private Timestamp searchTime;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "game_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonBackReference
    private Game game;

}
