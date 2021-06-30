package com.steammer.web.dto;

import com.steammer.domain.gameTag.GameTag;
import com.steammer.domain.games.Game;
import com.steammer.domain.tags.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class GameTagsSaveRequestDto {

    private Long gameTagKey;

    private Game game;

    private Tag tag;

    private List<Tag> tags = new ArrayList<>();

    @Builder
    public GameTagsSaveRequestDto(Long gameTagKey,Game game,Tag tag){
        this.gameTagKey = gameTagKey;
        this.game = game;
        this.tag = tag;
    }

    public GameTag toEntity(Game game,Tag tag) {
        return GameTag.builder()
                .game(game)
                .tag(tag)
                .build();
    }
}
