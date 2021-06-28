package com.steammer.domain.web;

import com.steammer.domain.games.Game;
import com.steammer.domain.games.GameRepository;
import com.steammer.web.dto.GamesSaveRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GameRepository gameRepository;

    @Test
    public void GameSaveEvent() throws Exception {
        Long gameId = Long.valueOf("837470");
        String gameName = "Untitled Goose Gam";
        String description = "어느 한적한 시골 마을에서 장난꾸러기 거위 한 마리를 조정해보세요.";
        String distributor = "House House";
        String evaluation = "압도적으로 긍정적";
        String imgUrl = "https://cdn.akamai.steamstatic.com/steam/apps/837470/header.jpg?t=1623284142";
        String videoUrl = "https://cdn.akamai.steamstatic.com/steam/apps/256800521/movie480_vp9.webm?t=1600878491";
        Date launchData = Date.valueOf("2020-9-24");

        GamesSaveRequestDto gameRequestDto = GamesSaveRequestDto.builder()
                .gameId(gameId)
                .gameName(gameName)
                .gameInfo(description)
                .distributor(distributor)
                .evaluation(evaluation)
                .imgUrl(imgUrl)
                .videoUrl(videoUrl)
                .launchDate(launchData)
                .build();

        String url = "http://localhost:"+port+"/api/v1/games";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, gameRequestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Game> all = gameRepository.findAll();
        assertThat(all.get(0).getGameName()).isEqualTo(gameName);
        assertThat(all.get(0).getLaunchDate()).isEqualTo(launchData);
    }
}


