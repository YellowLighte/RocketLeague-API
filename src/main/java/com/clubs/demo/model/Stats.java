package com.clubs.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    @OneToOne(mappedBy = "player")
    private Player player;


    public Stats(Long id, int saves, int goals, int assists) {
        this.id = id;
        this.saves = saves;
        this.goals = goals;
        this.assists = assists;
    }

    public Stats() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSaves() {
        return saves;
    }

    public void setSaves(int saves) {
        this.saves = saves;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "id=" + id +
                ", saves=" + saves +
                ", goals=" + goals +
                ", assists=" + assists +
                '}';
    }
}
