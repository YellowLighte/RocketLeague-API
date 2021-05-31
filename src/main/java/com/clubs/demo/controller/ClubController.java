package com.clubs.demo.controller;

import com.clubs.demo.model.Club;
import com.clubs.demo.model.Player;
import com.clubs.demo.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api")
public class ClubController {

     private ClubRepository clubRepository;

    @Autowired
    public void setClubRepository(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    // http://localhost:9092/api/hello
    @GetMapping(path = "/hello")
    public String helloWorld() {
        return "Hello world";
    }

    // http://localhost:9092/api/clubs
    @GetMapping(path = "/clubs")
    public List<Club> getClubs() {
       return clubRepository.findAll();
    }
}
