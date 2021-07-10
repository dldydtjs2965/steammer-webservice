package com.steammer.web.dto;

import com.steammer.domain.gameTag.GameTag;
import com.steammer.domain.games.Game;
import com.steammer.domain.tags.Tag;
import lombok.Getter;

@Getter
public class GameTagResponseDto {
    private long gameTagKey;
    private Tag tag;

    public GameTagResponseDto(GameTag entity) {
        this.gameTagKey = entity.getGameTagKey();
        this.tag = entity.getTag();
    }
}
