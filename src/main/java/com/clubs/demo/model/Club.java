package com.clubs.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clubs")
public class Club {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String captain;

    @Column
    private String primaryColor;

    @Column
    private String secondaryColor;

    @OneToMany(mappedBy = "club_users")
    private List<User> users;

    @OneToMany(mappedBy = "club_players")
    private List<Player> players;


    public Club(Long id, String name, String captain, String primaryColor, String secondaryColor) {
        this.id = id;
        this.name = name;
        this.captain = captain;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }

    public Club() {

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

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    @Override
    public String toString() {
        return "Club{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", captain='" + captain + '\'' +
                ", primaryColor='" + primaryColor + '\'' +
                ", secondaryColor='" + secondaryColor + '\'' +
                '}';
    }
}
