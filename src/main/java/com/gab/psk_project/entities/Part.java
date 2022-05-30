package com.gab.psk_project.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Parts")
@Getter @Setter
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private String name;

    @ManyToMany
    private List<Computer> computers;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;
}
