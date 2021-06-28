package com.gfnqueuechecker.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "server")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Server {

    @Id
    @Column(name = "server_id")
    @SequenceGenerator(name = "server_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "server_seq")
    @NotNull
    private Long id;

    @Column(name = "server_host", length = 3000)
    private String serverHost;

    @Column(name = "server_name")
    private String serverName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_group_id")
    @JsonIgnore
    private ServerGroup serverGroup;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "server")
    @JsonIgnore
    private List<CheckQueue> checkQueue;

}
