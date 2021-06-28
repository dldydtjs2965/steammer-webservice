package com.steammer.web.dto;

import com.steammer.domain.gameTag.GameTag;
import com.steammer.domain.games.Game;
import com.steammer.domain.tags.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GameTagsSaveRequestDto {

    private Long gameTagKey;

    private Game game;

    private Tag tag;

    @Builder
    public GameTagsSaveRequestDto(Long gameTagKey,Game game,Tag tag){
        this.gameTagKey = gameTagKey;
        this.game = game;
        this.tag = tag;
    }

    public GameTag toEntity() {
        return GameTag.builder()
                .gameTagKey(gameTagKey)
                .game(game)
                .tag(tag)
                .build();
    }
}
