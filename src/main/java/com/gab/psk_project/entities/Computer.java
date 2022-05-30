package com.gab.psk_project.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Computers")
@Getter @Setter
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private Float price;

    @ManyToMany(mappedBy = "computers")
    private List<Part> parts;

    @ManyToOne
    private Store store;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;
}
