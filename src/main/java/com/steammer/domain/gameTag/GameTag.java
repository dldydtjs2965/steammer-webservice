package com.steammer.domain.gameTag;

import com.steammer.domain.games.Game;
import com.steammer.domain.tags.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "GAME_TAGS")
public class GameTag {
    //게임 태그 키
    @Id
    @Column(name="GAME_TAG_KEY")
    private Long gameTagKey;

    // 게임 ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME_ID")
    private Game game;

    // tag ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAG_ID")
    private Tag tag;

    @Builder
    public GameTag(Game game, Tag tag){
        this.game = game;
        this.tag  = tag;
    }

}
