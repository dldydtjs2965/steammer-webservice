package com.steammer.service;

import com.steammer.domain.games.GameRepository;
import com.steammer.web.dto.GamesListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class GamesService {

    private final GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GamesListResponseDto> findAllDesc() {
        return gameRepository.findAllDesc().stream()
                .map(GamesListResponseDto::new)
                .collect(Collectors.toList());
    }
}
