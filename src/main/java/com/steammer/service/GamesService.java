package com.steammer.service;

import com.steammer.domain.games.Game;
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
    //게임들 페이징 조회
    @Transactional(readOnly = true)
    public List<LimitTagGameResponseDto> findAllPaging(Integer page, Integer size) {
        return gameRepository.findAllDesc(PageRequest.of(page, size)).stream()
                .map(LimitTagGameResponseDto::new)
                .collect(Collectors.toList());
    }
    //게임 태그만 따로 조회
    @Transactional(readOnly = true)
    public List<GameTagResponseDto> findGameTags(Long gameId) {
        return gameRepository.findById(gameId).get().getGameTags().stream()
                .map(GameTagResponseDto::new)
                .collect(Collectors.toList());
    }

    //PV 영상 조회 이벤트
    @Transactional(readOnly = true)
    public String findVideoUrl(Long gameId) {
        return gameRepository.findById(gameId).get().getVideoUrl();
    }

    //유저가 게임을 북마크 했을 때 사용되는 서비스
    @Transactional(readOnly = true)
    public Game findByGame(Long gameId){ return gameRepository.findById(gameId).get();}
}
