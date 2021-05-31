package com.clubs.demo.model;

import javax.persistence.*;

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


}
