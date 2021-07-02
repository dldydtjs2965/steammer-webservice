package com.steammer.web.dto;

import com.steammer.domain.gameTag.GameTag;
import com.steammer.domain.games.Game;
import com.steammer.domain.tags.Tag;
import lombok.Getter;

@Getter
public class GameTagsResponseDto {
    private long gameTagKey;
    private Game game;
    private Tag tag;

    public GameTagsResponseDto(GameTag entity) {
        this.gameTagKey = entity.getGameTagKey();
        this.game = entity.getGame();
        this.tag = entity.getTag();
    }
}
