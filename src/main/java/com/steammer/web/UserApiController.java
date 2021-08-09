package com.steammer.web;

import com.steammer.config.auth.LoginUser;
import com.steammer.config.auth.dto.SessionUser;
import com.steammer.domain.user.User;
import com.steammer.domain.games.Game;
import com.steammer.service.GamesService;
import com.steammer.service.UserService;
import com.steammer.web.dto.UserGameIdResponseDto;
import com.steammer.web.dto.UserGameSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    private final GamesService gamesService;

    private final HttpSession httpSession;

    //유저가 북마크한 게임 리스트 조회
    @GetMapping("api/v2/userGameList")
    public List<UserGameIdResponseDto> userGameIdResponseDto(@LoginUser SessionUser loginUser){
        Long userId = userService.findByUserId(loginUser.getEmail());
        return userService.findUserGameId(userId);
    }

    //유저 북마크 저장
    @PostMapping("api/v2/userGameSave")
    public Long userGameSave(@RequestBody Map<String,Long> request, @LoginUser SessionUser loginUser){
        //저장할 게임
        Game game = gamesService.findByGame(request.get("gameId"));
        //요청한 유저
        Long userId = userService.findByUserId(loginUser.getEmail());
        User user = userService.findByUser(userId);

        //저장할 Entity
        UserGameSaveRequestDto requestDto = UserGameSaveRequestDto.builder()
                                                .user(user)
                                                .game(game)
                                                .build();
        return userService.save(requestDto);
    }
    // 북마크 취소
    @DeleteMapping("api/v2/userGameCancel")
    public void userGameCancel(@RequestBody Map<String,Long> request, @LoginUser SessionUser loginUser) {
        //요청한 유저
        Long userId = userService.findByUserId(loginUser.getEmail());

        userService.cancelGame(userId, request.get("gameId"));
    }
}
