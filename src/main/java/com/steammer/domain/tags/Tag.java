package com.steammer.domain.tags;

import com.steammer.domain.gameTag.GameTag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "TAGS")
@NoArgsConstructor
public class Tag {
    // 태그 ID
    @Id
    @Column(name = "TAG_ID")
    private long tagId;
    // 태그 이름
    @Column(name = "TAG_NAME")
    private String tagName;

    @Builder
    public Tag(Long tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }
}
