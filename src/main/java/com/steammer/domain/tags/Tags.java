package com.steammer.domain.tags;

import com.steammer.domain.GameTags;
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
public class Tags {
    // 태그 ID
    @Id
    @Column(name = "TAG_ID")
    private long tagId;
    // 태그 이름
    @Column(name = "TAG_NAME")
    private String tagName;
    //  join 테이블
    @OneToMany(mappedBy = "tags")
    private List<GameTags> gameTags = new ArrayList<>();

    @Builder
    public Tags(Long tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }
}
