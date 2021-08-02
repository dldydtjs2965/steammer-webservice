package com.steammer.domain.userGame;

import com.steammer.domain.user.User;
import com.steammer.domain.games.Game;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "USER_GAMES")
@Getter
public class UserGame {
    @Id
    @Column(name="USER_GAME_KEY")
    private Long userGameKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME_ID")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public UserGame(Game game, User user){
        this.userGameKey = game.getGameId()+user.getId();
        this.game = game;
        this.user = user;
    }
}
