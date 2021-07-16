package com.steammer.domain.gameTag;

import com.steammer.domain.gameTag.GameTag;
import com.steammer.domain.gameTag.GameTagRepository;
import com.steammer.domain.games.Game;
import com.steammer.domain.games.GameRepository;
import com.steammer.domain.tags.Tag;
import com.steammer.domain.tags.TagRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameTagRepositoryTest {

    @Autowired
    GameTagRepository gameTagRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    TagRepository tagRepository;


    @Test
    public void 저장() {
        //given
        Optional<Game> game = gameRepository.findById(Long.valueOf("123456"));

        //게임 테스트 데이터 확인
        Game testGame =  game.orElse(null);

        Optional<Tag> tag= tagRepository.findById(Long.valueOf(1));

        Tag testTag = tag.orElse(null);

        GameTag gameTag = GameTag.builder()
                .game(testGame)
                .tag(testTag)
                .build();
        //when
        System.out.println("game_tag_key = "+gameTag.getGameTagKey());
        gameTagRepository.save(gameTag);

        //then
        List<GameTag> gameTagList = gameTagRepository.findAll();

        GameTag testGameTag = gameTagList.get(0);

        assertThat(testGameTag.getGame().getGameId()).isEqualTo(testGame.getGameId());

        assertThat(testGameTag.getTag().getTagId()).isEqualTo(testTag.getTagId());


    }
}
