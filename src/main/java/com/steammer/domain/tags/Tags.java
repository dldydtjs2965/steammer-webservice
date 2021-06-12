package com.steammer.domain.tags;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    @Builder
    public Tags(Long tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }
}
