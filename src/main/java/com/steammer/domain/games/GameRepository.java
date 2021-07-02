package com.steammer.domain.games;

import com.steammer.domain.gameTag.GameTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    @Query("SELECT g FROM Game g ORDER BY g.gameId DESC")
    List<Game> findAllDesc();
}
