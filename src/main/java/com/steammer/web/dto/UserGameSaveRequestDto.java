package com.steammer.web.dto;

import com.steammer.domain.user.User;
import com.steammer.domain.games.Game;
import com.steammer.domain.userGame.UserGame;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserGameSaveRequestDto {
    private Game game;
    private User user;

    @Builder
    public UserGameSaveRequestDto(Game game, User user){
        this.game = game;
        this.user = user;
    }

    public UserGame toEntity(){
        return UserGame.builder()
                .user(user)
                .game(game)
                .build();
    }

}
