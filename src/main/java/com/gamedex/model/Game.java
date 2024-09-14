package com.gamedex.model;


import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
@Table(name = "gamesnew")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String developer;
    private String producer;
    private String genre;

    @Column(name = "Operating_System")
    private String operating_system;

    @Column(name = "Date_Released")
    private String date;

    public Game(long id, String name, String developer, String producer, String genre, String operating_system, String date) {
        this.id = id;
        this.name = name;
        this.developer = developer;
        this.producer = producer;
        this.genre = genre;
        this.operating_system = operating_system;
        this.date = date;
    }

    public Game(String name)
    {
        this.name = name;
    }

    public Game() {

    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setOperating_system(String operating_system) {
        this.operating_system = operating_system;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
