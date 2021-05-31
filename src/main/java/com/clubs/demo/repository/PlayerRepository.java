package com.clubs.demo.repository;

import com.clubs.demo.model.Club;
import com.clubs.demo.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByName(String name);
}
