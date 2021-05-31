package com.clubs.demo.controller;

import com.clubs.demo.exception.InformationExistsException;
import com.clubs.demo.exception.InformationNotFoundException;
import com.clubs.demo.model.Club;
import com.clubs.demo.model.Player;
import com.clubs.demo.repository.ClubRepository;
import com.clubs.demo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api")
public class PlayerController {

    private PlayerRepository playerRepository;
    private ClubRepository clubRepository;

    @Autowired
    public void setPlayerRepository(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Autowired
    public void setClubRepository(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    // http://localhost:9092/api/players/{clubId}
    @PostMapping(path = "/players/{clubId}")
    public String createPlayer(@PathVariable Long clubId, @RequestBody Player newPlayer) {
        Optional<Player> player = playerRepository.findByName(newPlayer.getName());
        Optional<Club> club = clubRepository.findById(clubId);

        if (player.isPresent()) {
            throw new InformationExistsException("Player with name " + newPlayer.getName() + " already exists! Name " +
                    "must be unique.");
        }
        if (!club.isPresent()) {
            throw new InformationNotFoundException("Club with id " + clubId + " not found!");
        }

        newPlayer.setClub(club.get());
        playerRepository.save(newPlayer);
        return "Player " + newPlayer.getName() + " has been created and has been assigned to club " +
                club.get().getName() + "!";
    }

    // http://localhost:9092/api/players/name/{playerId}
    @PutMapping(path = "/players/name/{playerId}")
    public String updatePlayerName(@PathVariable Long playerId, @RequestBody String newName) {
        Optional<Player> player = playerRepository.findById(playerId);
        Optional<Player> existingPlayerName = playerRepository.findByName(newName);

        if (!player.isPresent()) {
            throw new InformationNotFoundException("Player with id " + playerId + " not found!");
        }
        if (existingPlayerName.isPresent()) {
            throw new InformationExistsException("Player with name " + newName + " already exists! Try a different " +
                    "name.");
        }

        player.get().setName(newName);
        playerRepository.save(player.get());

        return "Player name has been updated! New player name: " + player.get().getName();
    }

    // http://localhost:9092/api/players/system/{playerId}
    @PutMapping(path = "/players/system/{playerId}")
    public String updatePlayerSystem(@PathVariable Long playerId, @RequestBody String newSystem) {
        Optional<Player> player = playerRepository.findById(playerId);
        String existingPlayerSystem;

        if (!player.isPresent()) {
            throw new InformationNotFoundException("Player with id " + playerId + " not found!");
        }

        existingPlayerSystem = player.get().getSystem();
        if (player.get().getSystem().equals(newSystem)) {
            throw new InformationExistsException(player.get().getName() + " already has this system. No changes " +
                    "have been made.");
        }
        player.get().setSystem(newSystem);
        playerRepository.save(player.get());

        return "Player system has been updated! New player system: " + player.get().getSystem();
    }

    // http://localhost:9092/api/players/{playerId}/clubs/{clubId}
    @PutMapping(path = "/players/{playerId}/clubs/{clubId}")
    public String updatePlayerSystem(@PathVariable Long playerId, @PathVariable Long clubId) {
        Optional<Player> player = playerRepository.findById(playerId);
        Optional<Club> club = clubRepository.findById(clubId);
        Club playerCurrentClub;

        if (!player.isPresent()) {
            throw new InformationNotFoundException("Player with id " + playerId + " not found!");
        }
        if (!club.isPresent()) {
            throw new InformationNotFoundException("Club with id " + clubId + " not found!");
        }

        playerCurrentClub = clubRepository.findByName(player.get().getClub().getName()).get();

        if (club.get().equals(playerCurrentClub)) {
            throw new InformationExistsException(player.get().getName() + " is already a member of club " +
                    club.get().getName() + "!");
        }

        player.get().setClub(clubRepository.findById(clubId).get());
        playerRepository.save(player.get());

        return player.get().getName() + "'s new system is now " + player.get().getSystem() + "!";
    }

    // http://localhost:9092/api/players/{playerId}
    @GetMapping(path = "/players/{playerId}")
    public Club getPlayerClub(@PathVariable Long playerId) {
        Optional<Player> player = playerRepository.findById(playerId);

        if (!player.isPresent()) {
            throw new InformationNotFoundException("Player with id " + playerId + "doesn't exist!");
        }
        return player.get().getClub();
    }

    // http://localhost:9092/api/players/club/{clubId}
    @GetMapping(path = "/players/club/{clubId}")
    public List<Player> getPlayersByClub(@PathVariable Long clubId) {
        Optional<Club> club = clubRepository.findById(clubId);

        if (!club.isPresent()) {
            throw new InformationNotFoundException("Club with id " + clubId + " not found!");
        }

        List<Player> players = club.get().getPlayers();
        return players;
    }
}
