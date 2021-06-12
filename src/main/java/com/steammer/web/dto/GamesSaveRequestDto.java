package com.steammer.web.dto;

import com.steammer.domain.games.Games;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@NoArgsConstructor
public class GamesSaveRequestDto {

    private Long gameId;

    private String gameName;

    private String gameInfo;

    private Date launchDate;

    private String evaluation;

    private String imgUrl;

    private String videoUrl;

    private String devCompany;

    private String distributor;

    @Builder
    public GamesSaveRequestDto(Long gameId, String gameName, String gameInfo, Date launchDate, String evaluation, String imgUrl, String videoUrl, String devCompany, String distributor) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.gameInfo = gameInfo;
        this.launchDate = launchDate;
        this.evaluation = evaluation;
        this.imgUrl = imgUrl;
        this.videoUrl = videoUrl;
        this.devCompany = devCompany;
        this.distributor = distributor;
    }

    public Games toEntity() {
        return Games.builder()
                .gameId(gameId)
                .gameName(gameName)
                .gameInfo(gameInfo)
                .launchDate(launchDate)
                .evaluation(evaluation)
                .imgUrl(imgUrl)
                .videoUrl(videoUrl)
                .devCompany(devCompany)
                .distributor(distributor)
                .build();
    }
}
