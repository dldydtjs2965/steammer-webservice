package com.steammer.service;

import com.steammer.domain.gameTag.GameTag;
import com.steammer.domain.games.Game;
import com.steammer.domain.games.GameRepository;
import com.steammer.web.dto.GameLimitTagListResponseDto;
import com.steammer.web.dto.GameTagResponseDto;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GamesServiceTest extends TestCase {

    @Autowired
    GamesService gamesService;

    @Autowired
    GameRepository gameRepository;

    @Test
    public void testFindAllPaging() {
        List<GameLimitTagListResponseDto> games;

        //when
        games = gamesService.findAllPaging(0,9);

        //then
        assertThat(games.size()).isEqualTo(9);
    }

    @Test
    public void testFindGameTags() {
        //given
        List<GameTag> testGameTags = gameRepository.findById(Long.valueOf(1016920)).get().getGameTags();

        Collections.sort(testGameTags, new Comparator<GameTag>() {
            @Override
            public int compare(GameTag o1, GameTag o2) {
                if(o1.getGameTagKey()>o2.getGameTagKey()) {
                    return -1;
                }else if(o1.getGameTagKey()<o2.getGameTagKey()) {
                    return 1;
                }else {
                    return 0;
                }
            }
        });

        //when
        List<GameTagResponseDto> gameTags = gamesService.findGameTags(Long.valueOf(1016920));

        Collections.sort(gameTags, new Comparator<GameTagResponseDto>() {
            @Override
            public int compare(GameTagResponseDto o1, GameTagResponseDto o2) {
                if(o1.getGameTagKey()>o2.getGameTagKey()) {
                    return -1;
                }else if(o1.getGameTagKey()<o2.getGameTagKey()) {
                    return 1;
                }else {
                    return 0;
                }
            }
        });

        //then
        for (int i = 0; i<gameTags.size(); i++) {
            assertThat(testGameTags.get(i).getGameTagKey()).isEqualTo(gameTags.get(i).getGameTagKey());
        }
    }
}