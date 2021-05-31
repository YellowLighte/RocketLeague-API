package com.clubs.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String system;
}
