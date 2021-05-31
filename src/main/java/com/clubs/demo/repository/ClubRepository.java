package com.clubs.demo.repository;

import com.clubs.demo.model.Club;
import com.clubs.demo.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

}
