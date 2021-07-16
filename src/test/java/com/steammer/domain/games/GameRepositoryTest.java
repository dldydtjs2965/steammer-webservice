package com.steammer.domain.games;

import com.steammer.domain.games.Game;
import com.steammer.domain.games.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
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

    @Before
    public void cleanup() {
        gameRepository.deleteAll();
    }

    @Test
    public void create() {
        Long id = Long.valueOf("123456");
        String gameName = "test_name";
        String gameInfo = "test_info";
        Date launchDate = Date.valueOf("2021-05-05");
        String imgUrl = "test_url";
        String videoUrl = "test_url";
        String devCompany = "test-company";
        String distributor = "test-dis";
        //given
        Game game = Game.builder()
                .gameId(id)
                .gameName(gameName)
                .gameInfo(gameInfo)
                .launchDate(launchDate)
                .imgUrl(imgUrl)
                .videoUrl(videoUrl)
                .devCompany(devCompany)
                .distributor(distributor)
                .build();
        //when
        gameRepository.save(game);

        //then
        Optional<Game> gamesList = gameRepository.findById(id);

         //게임 테스트 데이터 확인
        Game games =  gamesList.orElse(null);

        assert games != null;
        assertThat(games.getGameId()).isEqualTo(id);
        assertThat(games.getGameName()).isEqualTo(gameName);
        assertThat(games.getGameInfo()).isEqualTo(gameInfo);
        assertThat(games.getLaunchDate()).isEqualTo(launchDate);
        assertThat(games.getImgUrl()).isEqualTo(imgUrl);
        assertThat(games.getVideoUrl()).isEqualTo(imgUrl);
        assertThat(games.getDevCompany()).isEqualTo(devCompany);
        assertThat(games.getDistributor()).isEqualTo(distributor);
    }

}
