package com.steammer.domain.userGame;

import com.steammer.domain.user.User;
import com.steammer.domain.user.UserRepository;
import com.steammer.domain.games.Game;
import com.steammer.domain.games.GameRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserGameRepositoryTest {
    @Autowired
    GameRepository gameRepository;

    @Autowired
    UserGameRepository userGameRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void 저장() {
        //given
        Optional<Game> game = gameRepository.findById(Long.valueOf("977950"));

        //게임 테스트 데이터 확인
        Game testGame =  game.orElse(null);

        Optional<User> tag= userRepository.findById(Long.valueOf(1));

        User testUser = tag.orElse(null);

        UserGame userGame = UserGame.builder()
                .game(testGame)
                .user(testUser)
                .build();
        //when
        System.out.println("game_tag_key = "+userGame.getUserGameKey());
        userGameRepository.save(userGame);

        //then
        List<UserGame> gameTagList = userGameRepository.findAll();

        UserGame testUserGame = gameTagList.get(0);

        assertThat(testUserGame.getGame().getGameId()).isEqualTo(testGame.getGameId());

        assertThat(testUserGame.getUser().getId()).isEqualTo(testUserGame.getUser().getId());

    }

}
