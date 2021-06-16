package com.steammer.web;

import com.steammer.service.GamesService;
import com.steammer.web.dto.GamesSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GamesApiController {
    private final GamesService gamesService;

    @PostMapping("/api/v1/games")
    public Long save(@RequestBody GamesSaveRequestDto requestDto){

        return gamesService.gameSave(requestDto);
    }
}
