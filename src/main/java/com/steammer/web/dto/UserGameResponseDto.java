package com.steammer.web.dto;

import com.steammer.domain.userGame.UserGame;
import lombok.Getter;

@Getter
public class UserGameResponseDto {
    private long userId;
    private long gameId;
    
    public UserGameResponseDto(UserGame entity){
        this.userId = entity.getUser().getId();
        this.gameId = entity.getGame().getGameId();
    }
}
