package com.clubs.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "player_stats")
public class Stats {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int saves;

    @Column
    private int goals;

    @Column
    private int assists;
}
