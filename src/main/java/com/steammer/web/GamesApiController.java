package com.steammer.web;

import com.steammer.service.GamesService;
import com.steammer.web.dto.GameLimitTagListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class GamesApiController {
    private final GamesService gamesService;

    @GetMapping("api/v1/gamesAll")
    public List<GameLimitTagListResponseDto> gameResponseDtoList (){
        return gamesService.findAllPaging(0,19);
    }
}
