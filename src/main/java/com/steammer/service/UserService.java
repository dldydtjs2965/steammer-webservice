package com.steammer.service;

import com.steammer.domain.userGame.UserGameRepository;
import com.steammer.web.dto.UserGameSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserGameRepository userGameRepository;

    @Transactional
    public Long save(UserGameSaveRequestDto requestDto){
        return userGameRepository.save(requestDto.toEntity()).getUserGameKey();
    }
}
