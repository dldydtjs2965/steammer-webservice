package com.steammer.domain;

import com.steammer.domain.games.Games;
import com.steammer.domain.tags.Tags;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "GAME_TAGS")
public class GameTags {
    //게임 태그 키
    @Id
    @Column(name="GAME_TAG_KEY")
    private Long gameTagKey;
    // 게임 ID
    @Column(name = "GAME_ID")
    private Long gameId;
    // tag ID
    @Column(name = "TAG_ID")
    private Long tagId;
    // join 테이블
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="GAME_ID")
    private Games games;
    // join 테이블
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="TAG_ID")
    private Tags tags;

    @Builder
    public GameTags(Long gameTagKey, Long gameId, Long tagId){
        this.gameTagKey = gameTagKey;
        this.gameId = gameId;
        this.tagId  = tagId;
    }

}
