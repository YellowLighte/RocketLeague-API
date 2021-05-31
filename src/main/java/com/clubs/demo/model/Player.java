package com.clubs.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    @OneToOne(mappedBy = "player")
    private Stats stats;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    public Player(Long id, String name, String system) {
        this.id = id;
        this.name = name;
        this.system = system;
    }

    public Player() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", system='" + system + '\'' +
                '}';
    }
}
