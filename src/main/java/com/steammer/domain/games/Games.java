package com.steammer.domain.games;

import com.steammer.domain.GameTags;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "GAMES")
@NoArgsConstructor
public class Games {
    @Id
    @Column(name = "GAME_ID")
    private Long gameId;

    @Column(name = "GAME_NAME")
    private String gameName;

    @Column(name = "GAME_INFO")
    private String gameInfo;

    @Column(name = "LAUNCH_DATE")
    private Date launchDate;

    @Column(name = "EVALUATION")
    private String evaluation;

    @Column(name = "IMG_URL")
    private String imgUrl;

    @Column(name = "VIDEO_URL")
    private String videoUrl;

    @Column(name = "DEV_COMPANY")
    private String devCompany;

    @Column(name = "DISTRIBUTOR")
    private String distributor;

    @OneToMany(mappedBy = "games")
    private List<GameTags> gameTags = new ArrayList<>();

    @Builder
    public Games(Long gameId, String gameName, String gameInfo, Date launchDate, String evaluation, String imgUrl, String videoUrl, String devCompany, String distributor) {
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
}
