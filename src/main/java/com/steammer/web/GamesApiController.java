package com.steammer.web;

import com.steammer.service.GamesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GamesApiController {
    private final GamesService gamesService;

}
