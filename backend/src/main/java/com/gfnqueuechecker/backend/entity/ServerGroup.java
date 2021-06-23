package com.gfnqueuechecker.backend.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "server_group")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class ServerGroup {

    @Id
    @Column(name = "server_group_id")
    @SequenceGenerator(name = "server_group_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "server_group_seq")
    @NotNull
    private Long id;

    @Column(name = "server_group_name")
    private String serverGroupName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "serverGroup", cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Server> servers;
}

