package com.steammer.web;

import com.steammer.config.auth.LoginUser;
import com.steammer.config.auth.dto.SessionUser;
import com.steammer.domain.User.User;
import com.steammer.domain.games.Game;
import com.steammer.service.GamesService;
import com.steammer.service.UserService;
import com.steammer.web.dto.UserGameResponseDto;
import com.steammer.web.dto.UserGameSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    private final GamesService gamesService;

    @GetMapping("api/v2/userGameList")
    public List<UserGameResponseDto> userGameResponseDto(@RequestParam Long id){
        return userService.findUserGame(id);
    }

    @PostMapping("api/v2/userGameSave")
    public Long userGameSave(@RequestBody Map<String,Long> request){
        Game game = gamesService.findByGame(request.get("gameId"));
        User user = userService.findByUser(request.get("userId"));
        UserGameSaveRequestDto requestDto = UserGameSaveRequestDto.builder()
                                                .user(user)
                                                .game(game)
                                                .build();
        return userService.save(requestDto);
    }

    @DeleteMapping("api/v2/userGameCancel")
    public void userGameCancel(@RequestBody Map<String,Long> request) {
        userService.cancelGame(request.get("userId"), request.get("gameId"));
    }
}
