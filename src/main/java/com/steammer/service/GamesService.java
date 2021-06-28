package com.steammer.service;

import com.steammer.domain.gameTag.GameTagRepository;
import com.steammer.domain.games.GameRepository;
import com.steammer.domain.tags.TagRepository;
import com.steammer.web.dto.GamesSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GamesService {
    private final GameRepository gameRepository;
    private final TagRepository tagRepository;
    private final GameTagRepository gameTagRepository;
    @Transactional
    public Long gameSave(GamesSaveRequestDto gamesRequestDto){
        return gameRepository.save(gamesRequestDto.toEntity()).getGameId();
    }
}
