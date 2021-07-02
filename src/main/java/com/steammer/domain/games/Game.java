package com.steammer.domain.games;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.steammer.domain.gameTag.GameTag;
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
//stack overflow 방지
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Game {

    // 게임 아이디(PK)
    @Id
    @Column(name = "GAME_ID")
    private Long gameId;
    // 게임 이름
    @Column(name = "GAME_NAME")
    private String gameName;
    // 게임 정보
    @Column(name = "GAME_INFO")
    private String gameInfo;
    // 게임 런칭 날짜
    @Column(name = "LAUNCH_DATE")
    private Date launchDate;
    // 게임 평가
    @Column(name = "EVALUATION")
    private String evaluation;
    // 게임 이미지
    @Column(name = "IMG_URL")
    private String imgUrl;
    //게임 영상
    @Column(name = "VIDEO_URL")
    private String videoUrl;
    //게임 개발 회사
    @Column(name = "DEV_COMPANY")
    private String devCompany;
    //게임 배급사
    @Column(name = "DISTRIBUTOR")
    private String distributor;

    @OneToMany(mappedBy = "game")
    private List<GameTag> gameTags = new ArrayList<>();

    @Builder
    public Game(Long gameId, String gameName, String gameInfo, Date launchDate, String evaluation, String imgUrl, String videoUrl, String devCompany, String distributor) {
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
