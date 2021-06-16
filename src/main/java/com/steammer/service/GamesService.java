package com.steammer.service;

import com.steammer.domain.GameTags;
import com.steammer.domain.GameTagsRepository;
import com.steammer.domain.games.GamesRepository;
import com.steammer.domain.tags.TagsRepository;
import com.steammer.web.dto.GameTagsSaveRequestDto;
import com.steammer.web.dto.GamesSaveRequestDto;
import com.steammer.web.dto.TagsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GamesService {
    private final GamesRepository gamesRepository;
    private final TagsRepository tagsRepository;
    private final GameTagsRepository gameTagsRepository;
    @Transactional
    public Long gameSave(GamesSaveRequestDto gamesRequestDto, List<TagsSaveRequestDto> tagsRequestDtoList){
        Long gameId = gamesRepository.save(gamesRequestDto.toEntity()).getGameId();

        for(TagsSaveRequestDto tagsSaveRequestDto: tagsRequestDtoList){
            GameTags gameTags = GameTagsSaveRequestDto.builder()
                    .gameId(gameId)
                    .tagId(tagsSaveRequestDto.getTagId())
                    .build().toEntity();

            tagsRepository.save(tagsSaveRequestDto.toEntity());

            gameTagsRepository.save(gameTags);
        }

        return gameId;
    }
}
