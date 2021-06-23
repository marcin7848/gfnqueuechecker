package com.gfnqueuechecker.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "check_queue")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class CheckQueue {

    @Id
    @Column(name = "check_queue_id")
    @SequenceGenerator(name = "check_queue_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "check_queue_seq")
    @NotNull
    private Long id;

    @Column(name = "search_key")
    private String searchKey;

    @Column(name = "add_time")
    private Timestamp addTime;

    @Column(name = "role")
    private Long role;

    @Column(name = "process")
    private Long process;

    @Column(name = "position_in_queue")
    private Long positionInQueue;

    @Column(name = "eta")
    private Long eta;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "game_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonBackReference
    private Game game;
}
