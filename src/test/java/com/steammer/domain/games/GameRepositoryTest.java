package com.steammer.domain.games;

import com.steammer.domain.games.Game;
import com.steammer.domain.games.GameRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameRepositoryTest {

    @Autowired
    GameRepository gameRepository;

    @Test
    public void create() {

        //given
        Game game = Game.builder()
                .gameId(Long.valueOf("123456"))
                .gameName("test_name")
                .gameInfo("test_info")
                .launchDate(Date.valueOf("2021-05-05"))
                .imgUrl("test_url")
                .videoUrl("test-url")
                .devCompany("test-company")
                .distributor("test-dis")
                .build();
        //when
        gameRepository.save(game);

        //then
        Optional<Game> gamesList = gameRepository.findById(Long.valueOf("12345"));

         //게임 테스트 데이터 확인
        Game games =  gamesList.orElse(null);

        assert games != null;
        assertThat(games.getGameId()).isEqualTo(Long.valueOf("12345"));
        assertThat(games.getGameName()).isEqualTo("test_name");
        assertThat(games.getGameInfo()).isEqualTo("test_info");
        assertThat(games.getLaunchDate()).isEqualTo(Date.valueOf("2021-05-05"));
        assertThat(games.getImgUrl()).isEqualTo("test_url");
        assertThat(games.getVideoUrl()).isEqualTo("test-url");
        assertThat(games.getDevCompany()).isEqualTo("test-company");
        assertThat(games.getDistributor()).isEqualTo("test-dis");
    }

//    @Test
//    public void read() {
//        Optional<Games> user = gamesRepository.findById(2L);
//        user.ifPresent( selectUser -> {
//            // ifPresent: 값이 있다면, 가져옴
//            System.out.println(selectUser.toString());
//        });
//    }

}
