package com.steammer.domain;

import com.steammer.domain.games.Games;
import com.steammer.domain.games.GamesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GamesRepositoryTest {

    @Autowired
    GamesRepository gamesRepository;

    @Test
    public void create() {
        Games game = Games.builder()
                .gameId(Long.valueOf("12345"))
                .gameName("test_name")
                .gameInfo("test_info")
                .launchDate(Date.valueOf("2021-05-05"))
                .imgUrl("test_url")
                .videoUrl("test-url")
                .devCompany("test-company")
                .distributor("test-dis")
                .build();

        gamesRepository.save(game);


        Optional<Games> gamesList = gamesRepository.findById(Long.valueOf("12345"));

         //게임 테스트 데이터 확인
        Games games =  gamesList.orElse(null);

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
