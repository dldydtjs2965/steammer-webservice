package com.steammer.domain;

import com.steammer.domain.games.Games;
import com.steammer.domain.tags.Tags;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "GAME_TAGS")
public class GameTags {

    @Id
    @Column(name="GAME_TAG_KEY")
    private Long gameTagKey;

    @Column(name = "GAME_ID")
    private Long gameId;

    @Column(name = "TAG_ID")
    private Long tagId;

    @ManyToOne
    @JoinColumn(name="GAME_ID")
    private Games games;

    @ManyToOne
    @JoinColumn(name="TAG_ID")
    private Tags tags;
}
