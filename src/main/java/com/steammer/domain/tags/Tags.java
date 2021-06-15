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
    @Id
    @Column(name = "TAG_ID")
    private long tagId;

    @Column(name = "TAG_NAME")
    private String tagName;

    @OneToMany(mappedBy = "tags")
    private List<GameTags> gameTags = new ArrayList<>();

    @Builder
    public Tags(Long tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }
}
