package com.steammer.service;

import com.steammer.domain.gameTag.GameTag;
import com.steammer.domain.games.GameRepository;
import com.steammer.web.dto.LimitTagGameResponseDto;
import com.steammer.web.dto.GameTagResponseDto;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.*;

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
        List<LimitTagGameResponseDto> games2;

        //when
        games2 = gamesService.findAllPaging(1, 9);

        Long testGameId = gameRepository.findAllDesc(PageRequest.of(1,9)).get(1).getGameId();

        //then
        assertThat(games2.size()).isEqualTo(9);
        assertThat(games2.get(1).getGameId()).isEqualTo(testGameId);
    }

    @Test
    public void testFindGameTags() {
        //when
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

    @Test
    public void testFindVideoUrl(){
        //when
        String videoUrl = gamesService.findVideoUrl(977950L);

        String realVideoUrl = "https://cdn.akamai.steamstatic.com/steam/apps/256741361/movie480.webm?t=1548371782";
        //then
        assertThat(videoUrl).isEqualTo(realVideoUrl);
    }
}