package com.steammer.web;

import com.steammer.service.GamesService;
import com.steammer.web.dto.GameLimitTagListResponseDto;
import com.steammer.web.dto.GameTagResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class GamesApiController {
    private final GamesService gamesService;

    @PostMapping("api/appendGames")
    public Map<String,List<GameLimitTagListResponseDto>> gamesReResponse (@RequestBody Integer page){
        HashMap<String,List<GameLimitTagListResponseDto>> map = new HashMap<>();
        map.put("games",gamesService.findAllPaging(page));
        System.out.println("page="+page);
        return map;
    }

    @PostMapping("api/gameTagResponse")
    public Map<String,List<GameTagResponseDto>> gameTagResponseDtoList(@RequestBody Long gameId) {
        HashMap<String,List<GameTagResponseDto>> map = new HashMap<String,List<GameTagResponseDto>>();
        map.put("gameTags",gamesService.findGameTags(gameId));
        return map;
    }
}
