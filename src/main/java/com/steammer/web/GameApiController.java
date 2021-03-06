package com.steammer.web;

import com.steammer.service.GamesService;
import com.steammer.web.dto.LimitTagGameResponseDto;
import com.steammer.web.dto.GameTagResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class GameApiController {
    private final GamesService gamesService;
    //스크롤된 다음 게임 리스트 return
    @PostMapping("api/v1/appendGames")
    public List<LimitTagGameResponseDto> gamesReResponse (@RequestBody Map<String,Integer> request){
        return gamesService.findAllPaging(request.get("page"), request.get("size"));
    }
    //한 게임의 모든 태그 조회
    @GetMapping("api/v1/gameTagResponse")
    public List<GameTagResponseDto> gameTagResponseDtoList(@RequestParam Long gameId) {
        return gamesService.findGameTags(gameId);
    }
    //게임 PV영상 조회
    @GetMapping("api/v1/videoResponse")
    public String gameVideoUrlResponse(@RequestParam Long gameId) {
        return gamesService.findVideoUrl(gameId);
    }
}
