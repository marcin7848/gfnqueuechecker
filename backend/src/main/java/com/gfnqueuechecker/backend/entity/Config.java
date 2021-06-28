package com.gfnqueuechecker.backend.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "config")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Config {

    @Id
    @Column(name = "config_id")
    @SequenceGenerator(name = "config_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "config_seq")
    @NotNull
    private Long id;

    @Column(name = "config_name")
    private String configName;

    @Column(name = "config_value", length = 3000)
    private String configValue;

}
