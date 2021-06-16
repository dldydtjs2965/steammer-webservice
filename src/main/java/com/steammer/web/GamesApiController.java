package com.steammer.web;

import com.steammer.service.GamesService;
import com.steammer.web.dto.GamesSaveRequestDto;
import com.steammer.web.dto.TagsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class GamesApiController {
    private final GamesService gamesService;

    @PostMapping("/api/v1/games")
    public Long save(@RequestBody GamesSaveRequestDto requestDto, @RequestBody List<TagsSaveRequestDto> tagsSaveRequestDtoList){

        return gamesService.gameSave(requestDto, tagsSaveRequestDtoList);
    }
}
