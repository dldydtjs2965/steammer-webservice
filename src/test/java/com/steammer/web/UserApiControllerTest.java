package com.steammer.web;

import com.steammer.domain.user.User;
import com.steammer.domain.user.UserRepository;
import com.steammer.domain.games.Game;
import com.steammer.domain.games.GameRepository;
import com.steammer.domain.userGame.UserGame;
import com.steammer.domain.userGame.UserGameRepository;
import com.steammer.web.dto.UserGameSaveRequestDto;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class UserApiControllerTest extends TestCase {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGameRepository userGameRepository;

    @LocalServerPort
    private int port;


    @Test
    public void testUserGameSave() {
        String url = "http://localhost:"+port+"/api/v2/userGameSave";

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
        String url = "http://localhost:"+port+"/api/v2/userGameCancel";

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

        request.put("gameId", 977950L);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Map<String,Long>> entity = new HttpEntity<>(request,headers);

        //when
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE,entity,String.class);
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}