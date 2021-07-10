package com.steammer.service;

import com.steammer.domain.gameTag.GameTagRepository;
import com.steammer.domain.games.GameRepository;
import com.steammer.web.dto.GameLimitTagListResponseDto;
import com.steammer.web.dto.GameTagResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class GamesService {

    private final GameRepository gameRepository;

    private final GameTagRepository gameTagRepository;

    @Transactional(readOnly = true)
    public List<GameLimitTagListResponseDto> findAllPaging(int firstIndex, int lastIndex) {
        return gameRepository.findAll(PageRequest.of(firstIndex, lastIndex)).stream()
                .map(GameLimitTagListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<GameTagResponseDto> findGameTags(Long gameId) {
        return gameRepository.findById(gameId).get().getGameTags().stream()
                .map(GameTagResponseDto::new)
                .collect(Collectors.toList());
    }
}
