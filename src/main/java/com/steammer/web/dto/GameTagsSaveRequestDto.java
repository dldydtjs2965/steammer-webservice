package com.steammer.web.dto;

import com.steammer.domain.GameTags;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GameTagsSaveRequestDto {

    private Long gameTagKey;

    private Long gameId;

    private Long tagId;

    @Builder
    public GameTagsSaveRequestDto(Long gameId,Long tagId){
        this.gameTagKey = gameId + tagId;
        this.gameId = gameId;
        this.tagId = gameId;
    }

    public GameTags toEntity() {
        return GameTags.builder()
                .gameTagKey(gameTagKey)
                .gameId(gameId)
                .tagId(tagId)
                .build();
    }
}
