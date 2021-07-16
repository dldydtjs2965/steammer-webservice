package com.steammer.service;

import com.steammer.domain.games.GameRepository;
import com.steammer.web.dto.LimitTagGameResponseDto;
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

    @Transactional(readOnly = true)
    public List<LimitTagGameResponseDto> findAllPaging(Integer page, Integer size) {
        return gameRepository.findAllDesc(PageRequest.of(page, size)).stream()
                .map(LimitTagGameResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<GameTagResponseDto> findGameTags(Long gameId) {
        return gameRepository.findById(gameId).get().getGameTags().stream()
                .map(GameTagResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public String findVideoUrl(Long gameId) {
        return gameRepository.findById(gameId).get().getVideoUrl();
    }
}
