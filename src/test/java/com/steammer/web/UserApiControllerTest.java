package com.steammer.web;

import com.steammer.config.auth.dto.SessionUser;
import com.steammer.domain.user.Role;
import com.steammer.domain.user.User;
import com.steammer.domain.user.UserRepository;
import com.steammer.domain.games.Game;
import com.steammer.domain.games.GameRepository;
import com.steammer.domain.userGame.UserGame;
import com.steammer.domain.userGame.UserGameRepository;
import com.steammer.service.UserService;
import com.steammer.web.dto.UserGameSaveRequestDto;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiControllerTest extends TestCase {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserGameRepository userGameRepository;

    @Autowired
    private WebApplicationContext context;

    protected MockHttpSession session;

    private SessionUser sessionUser;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        User user = userRepository.findByEmail("test@gmail.com").get();

        userRepository.save(user);

        sessionUser = new SessionUser(user);

        session = new MockHttpSession();

        session.setAttribute("user", sessionUser);
    }

    @After // 2
    public void clean(){
        session.clearAttributes();
    }

    @LocalServerPort
    private int port;


    @Test
    @WithMockUser(roles = "USER")
    public void testUserGameSave() throws Exception {
        String url = "http://localhost:"+port+"/api/v2/userGameSave";

        // given
        Game game = gameRepository.findById(977950L).get();
        Long request = game.getGameId();

        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        String content = request.toString();

        // when
        mockMvc.perform(post(url)
                        .session(session) // 추가
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //then
        List<Game> all = userGameRepository.findAllByUserWishGame(userService.findByUserId(sessionUser.getEmail()));
        assertThat(all.get(0).getGameId()).isEqualTo(game.getGameId());
    }

    @Test
    public void testUserGameCancel() throws Exception{
        String url = "http://localhost:"+port+"/api/v2/userGameCancel";

        // given
        Game game = gameRepository.findById(977950L).get();
        Long request = game.getGameId();

        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        String content = request.toString();

        // when
        mockMvc.perform(delete(url)
                        .session(session) // 추가
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //then
        Boolean result = userGameRepository.findAllByUserWishGame(userService.findByUserId(sessionUser.getEmail())).isEmpty();
        assertThat(result).isEqualTo(true);
    }
}