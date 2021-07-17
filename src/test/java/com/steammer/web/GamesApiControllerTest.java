package com.steammer.web;

import com.steammer.domain.User.User;
import com.steammer.domain.User.UserRepository;
import com.steammer.domain.gameTag.GameTag;
import com.steammer.domain.games.Game;
import com.steammer.domain.games.GameRepository;
import com.steammer.domain.userGame.UserGame;
import com.steammer.domain.userGame.UserGameRepository;
import com.steammer.service.UserService;
import com.steammer.web.dto.UserGameSaveRequestDto;
import junit.framework.TestCase;
import net.minidev.json.JSONObject;
import org.junit.After;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class GamesApiControllerTest extends TestCase {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserGameRepository userGameRepository;

    @LocalServerPort
    private int port;

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void testGamesReResponse() {
        //given
        Map<String,Integer> request = new HashMap<>();
        request.put("page", 0);
        request.put("size", 9);

        String url = "http://localhost:"+port+"/api/v1/appendGames";

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

    @Test
    public void testGameVideoResponse() {
        String url = "http://localhost:"+port+"/api/v1/videoResponse?gameId=892970";
        //when
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("https://cdn.akamai.steamstatic.com/steam/apps/256820008/movie480_vp9.webm?t=1612278985");
    }


    @Test
    public void testUserGameSave() {
        String url = "http://localhost:"+port+"/api/v1/userGameSave";

        // given
        Game game = gameRepository.findById(977950L).get();
        User user = userRepository.findById(1L).get();
        UserGameSaveRequestDto requestDto = UserGameSaveRequestDto.builder()
                .user(user)
                .game(game)
                .build();
        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<UserGame> all = userGameRepository.findAll();
        assertThat(all.get(0).getGame()).isEqualTo(game);
        assertThat(all.get(0).getUser()).isEqualTo(user);
    }

    @Test
    public void testUserGameCancel() {
        String url = "http://localhost:"+port+"/api/v1/userGameCancel";

        // given
        Game game = gameRepository.findById(977950L).get();
        User user = userRepository.findById(1L).get();
        UserGame userGame = UserGame.builder()
                                .user(user)
                                .game(game)
                                .build();

        userGameRepository.save(userGame);
        //when
        Map<String,Long> request = new HashMap<>();

        request.put("userId", 1L);
        request.put("gameId", 977950L);
        //when
        restTemplate.delete(url,request);
        //then
        assertThat(userGameRepository.findAll().isEmpty()).isTrue();
    }
}