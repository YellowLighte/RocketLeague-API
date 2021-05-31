package com.clubs.demo.controller;

import com.clubs.demo.exception.InformationExistsException;
import com.clubs.demo.exception.InformationNotFoundException;
import com.clubs.demo.model.Club;
import com.clubs.demo.model.Player;
import com.clubs.demo.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api")
public class ClubController {

     private ClubRepository clubRepository;

    @Autowired
    public void setClubRepository(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    // http://localhost:9092/api/clubs
    @GetMapping(path = "/clubs")
    public List<Club> getClubs() {
       return clubRepository.findAll();
    }

    // http://localhost:9092/api/clubs/{clubId}
    @GetMapping(path = "/clubs/{clubId}")
    public Optional<Club> getClub(@PathVariable Long clubId) {
        Optional<Club> club = clubRepository.findById(clubId);
        if (!club.isPresent()) {
            throw new InformationNotFoundException("Club with id " + clubId + " not found!");
        }

        return club;
    }

    // http://localhost:9092/api/clubs/
    @PostMapping(path = "/clubs")
    public String createClub(@RequestBody Club newClub) {
        Optional<Club> existingClub = clubRepository.findByName(newClub.getName());
        if (existingClub.isPresent()) {
            throw new InformationExistsException("Club with name " + newClub.getName() + " already exists! Try " +
                    "selecting a different name.");
        }

        clubRepository.save(newClub);
        return "Club " + newClub.getName() + " has been created!";
    }

    @PutMapping(path = "/clubs/{clubId}")
    public String updateClub(@PathVariable Long clubId, @RequestBody Club updatedClub) {

        Club club = this.getClub(clubId).get();
        if (club == null) {
            throw new InformationNotFoundException("Club with id " + clubId + " not found!");
        }
        club.setCaptain(updatedClub.getCaptain());
        club.setName(updatedClub.getName());
        club.setPrimaryColor(updatedClub.getPrimaryColor());
        club.setSecondaryColor(updatedClub.getSecondaryColor());
        clubRepository.save(club);
        return "Club " + club.getName() + " has been updated!";

    }

    @DeleteMapping(path = "/clubs/{clubId}")
    public String deleteClub(@PathVariable Long clubId) {
//        Club club = clubRepository.findById(clubId).get();
        Optional<Club> club = clubRepository.findById(clubId);

        if (!club.isPresent()) {
            throw new InformationNotFoundException("Club with id " + clubId + " not found!");
        }

        clubRepository.deleteById(clubId);
        return "Club " + club.get().getName() + " has been deleted!";
    }
}
