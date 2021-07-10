package com.steammer.web;

import com.steammer.service.GamesService;
import com.steammer.web.dto.GameLimitTagListResponseDto;
import com.steammer.web.dto.GameTagResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class GamesApiController {
    private final GamesService gamesService;

    @GetMapping("api/gamesAll")
    public List<GameLimitTagListResponseDto> gameResponseDtoList (){
        return gamesService.findAllPaging(0,19);
    }

    @PostMapping("api/gameTagResponse")
    public Map<String,List<GameTagResponseDto>> gameTagResponseDtoList(Long gameId) {
        HashMap<String,List<GameTagResponseDto>> map = new HashMap<String,List<GameTagResponseDto>>();
        map.put("gameTags",gamesService.findGameTags(gameId));
        return map;
    }
}
