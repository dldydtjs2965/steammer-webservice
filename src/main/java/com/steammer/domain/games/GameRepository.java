package com.steammer.domain.games;

import org.springframework.data.repository.PagingAndSortingRepository;


public interface GameRepository extends PagingAndSortingRepository<Game, Long> {
}
