package com.steammer.service;

import com.steammer.domain.User.User;
import com.steammer.domain.User.UserRepository;
import com.steammer.domain.games.Game;
import com.steammer.domain.games.GameRepository;
import com.steammer.domain.userGame.UserGameRepository;
import com.steammer.web.dto.UserGameSaveRequestDto;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest extends TestCase {

    @Autowired
    UserService userService;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserGameRepository userGameRepository;


    @Test
    public void testSave() {
        // given
        Game game = gameRepository.findById(977950L).get();
        User user = userRepository.findById(1L).get();
        UserGameSaveRequestDto requestDto = UserGameSaveRequestDto.builder()
                .user(user)
                .game(game)
                .build();
        //when
        Long id = userService.save(requestDto);

        //then
        Long testId = userGameRepository.findAll().get(0).getUserGameKey();

        assertThat(id).isEqualTo(testId);
    }

    @Test
    public void testCancelGame() {
        // given
        Game game = gameRepository.findById(977950L).get();
        User user = userRepository.findById(1L).get();
        UserGameSaveRequestDto requestDto = UserGameSaveRequestDto.builder()
                .user(user)
                .game(game)
                .build();
        Long id = userService.save(requestDto);

        //then
        userService.cancelGame(user.getId(),game.getGameId());

        //when
        assertThat(userRepository.findById(id)).isEmpty();
    }
}