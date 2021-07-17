package com.steammer.web;

import com.steammer.service.GamesService;
import com.steammer.service.UserService;
import com.steammer.web.dto.LimitTagGameResponseDto;
import com.steammer.web.dto.GameTagResponseDto;
import com.steammer.web.dto.UserGameSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class GamesApiController {
    private final GamesService gamesService;

    private final UserService userService;

    @PostMapping("api/v1/appendGames")
    public Map<String,List<LimitTagGameResponseDto>> gamesReResponse (@RequestBody Map<String,Integer> request){
        HashMap<String,List<LimitTagGameResponseDto>> map = new HashMap<>();
        map.put("games",gamesService.findAllPaging(request.get("page"), request.get("size")));;
        return map;
    }

    @PostMapping("api/v1/gameTagResponse")
    public Map<String,List<GameTagResponseDto>> gameTagResponseDtoList(@RequestBody Long gameId) {
        HashMap<String,List<GameTagResponseDto>> map = new HashMap<String,List<GameTagResponseDto>>();
        map.put("gameTags",gamesService.findGameTags(gameId));
        return map;
    }

    @GetMapping("api/v1/videoResponse")
    public String gameVideoUrlResponse(@RequestParam Long gameId) {
        return gamesService.findVideoUrl(gameId);
    }

    @PostMapping("api/v1/userGameSave")
    public Long userGameSave(@RequestBody UserGameSaveRequestDto requestDto){
        return userService.save(requestDto);
    }

    @DeleteMapping("api/v1/userGameCancel")
    public void userGameCancel(@RequestBody Map<String,Long> request) {
        userService.cancelGame(request.get("userId"), request.get("gameId"));
    }
}
