package com.steammer.web;

import com.steammer.domain.gameTag.GameTag;
import com.steammer.domain.games.GameRepository;
import junit.framework.TestCase;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class GamesApiControllerTest extends TestCase {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GameRepository gameRepository;

    @LocalServerPort
    private int port;

    @Test
    public void testGamesReResponse() {
        //given
        Integer request = 1;

        String url = "http://localhost:"+port+"/api/appendGames";

        //when
        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(url, request, JSONObject.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().toJSONString()).contains("games");
    }

    @Test
    public void testGameTagResponseDtoList() {
        //given
        Long gameId = Long.valueOf(1016920);

        String url = "http://localhost:"+port+"/api/gameTagResponse";

        List<GameTag> gameTags = gameRepository.findById(gameId).get().getGameTags();
        //when
        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(url,gameId,JSONObject.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        for (GameTag gameTag : gameTags) {
            assertThat(responseEntity.getBody().toJSONString()).contains(gameTag.getTag().getTagName());
        }
    }
}