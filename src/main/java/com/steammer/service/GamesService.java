package com.steammer.service;

import com.steammer.domain.games.GamesRepository;
import com.steammer.web.dto.GamesSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GamesService {
    private final GamesRepository gamesRepository;

    @Transactional
    public Long gameSave(GamesSaveRequestDto gamesRequestDto){
        return gamesRepository.save(gamesRequestDto.toEntity()).getGameId();
    }
}
