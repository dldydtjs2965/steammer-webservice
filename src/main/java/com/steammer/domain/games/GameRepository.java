package com.steammer.domain.games;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface GameRepository extends PagingAndSortingRepository<Game, Long> {

    @Override
    @Query("SELECT g FROM Game g ORDER BY g.gameId DESC")
    List<Game> findAll();
}
