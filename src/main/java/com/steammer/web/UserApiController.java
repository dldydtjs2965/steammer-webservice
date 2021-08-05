package com.steammer.web;

import com.steammer.domain.user.User;
import com.steammer.domain.games.Game;
import com.steammer.service.GamesService;
import com.steammer.service.UserService;
import com.steammer.web.dto.UserGameIdResponseDto;
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
    //유저가 북마크한 게임 리스트 조회
    @GetMapping("api/v2/userGameList")
    public List<UserGameIdResponseDto> userGameIdResponseDto(@RequestParam Long id){
        return userService.findUserGameId(id);
    }

    //유저 북마크 저장
    @PostMapping("api/v2/userGameSave")
    public Long userGameSave(@RequestBody Map<String,Long> request){
        //저장할 게임
        Game game = gamesService.findByGame(request.get("gameId"));
        //요청한 유저
        User user = userService.findByUser(request.get("userId"));
        //저장할 Entity
        UserGameSaveRequestDto requestDto = UserGameSaveRequestDto.builder()
                                                .user(user)
                                                .game(game)
                                                .build();
        return userService.save(requestDto);
    }
    // 북마크 취소
    @DeleteMapping("api/v2/userGameCancel")
    public void userGameCancel(@RequestBody Map<String,Long> request) {
        userService.cancelGame(request.get("userId"), request.get("gameId"));
    }
}
