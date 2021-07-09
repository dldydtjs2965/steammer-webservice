package com.steammer.service;

import com.steammer.domain.games.Game;
import com.steammer.domain.games.GameRepository;
import com.steammer.web.dto.GameLimitTagListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class GamesService {

    private final GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameLimitTagListResponseDto> findAllPaging(int firstIndex, int lastIndex) {
        return gameRepository.findAll(PageRequest.of(firstIndex, lastIndex)).stream()
                .map(GameLimitTagListResponseDto::new)
                .collect(Collectors.toList());
    }
}
