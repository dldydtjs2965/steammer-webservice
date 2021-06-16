package com.steammer.web.dto;

import com.steammer.domain.tags.Tags;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TagsSaveRequestDto {
    private Long tagId;
    private String tagName;

    @Builder
    TagsSaveRequestDto(Long tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public Tags toEntity() {
        return Tags.builder()
                .tagId(tagId)
                .tagName(tagName)
                .build();
    }
}
