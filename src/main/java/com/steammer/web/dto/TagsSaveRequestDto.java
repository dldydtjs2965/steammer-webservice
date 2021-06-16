package com.steammer.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TagsSaveRequestDto {
    private Long tagId;
    private String tagName;

    @Builder TagsSaveRequestDto(Long tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;

    }
}
