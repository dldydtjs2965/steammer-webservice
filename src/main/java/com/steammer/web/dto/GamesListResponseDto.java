package com.steammer.web.dto;

import com.steammer.domain.gameTag.GameTag;
import com.steammer.domain.gameTag.GameTagRepository;
import com.steammer.domain.games.Game;
import com.steammer.domain.tags.Tag;
import lombok.Getter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GamesListResponseDto {
    // 게임 아이디(PK)
    private Long gameId;
    // 게임 이름
    private String gameName;
    // 게임 정보
    private String gameInfo;
    // 게임 런칭 날짜
    private Date launchDate;
    // 게임 평가
    private String evaluation;
    // 게임 이미지
    private String imgUrl;
    //게임 영상
    private String videoUrl;
    //게임 개발 회사
    private String devCompany;
    //게임 배급사
    private String distributor;

    private List<Tag> gameTags = new ArrayList<>();

    public GamesListResponseDto(Game entity) {
        this.gameId = entity.getGameId();
        this.gameName = entity.getGameName();
        this.gameInfo = entity.getGameInfo();
        this.launchDate = entity.getLaunchDate();
        this.evaluation = entity.getEvaluation();
        this.imgUrl = entity.getImgUrl();
        this.videoUrl = entity.getVideoUrl();
        this.devCompany = entity.getDevCompany();
        this.distributor = entity.getDistributor();
        this.gameTags = entity.getGameTags().stream().limit(2L).map(GameTag::getTag).collect(Collectors.toList());
    }
}
