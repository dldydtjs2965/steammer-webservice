package com.steammer.web;

import com.steammer.config.auth.LoginUser;
import com.steammer.config.auth.dto.SessionUser;
import com.steammer.service.GamesService;
import com.steammer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final GamesService gamesService;
    private final UserService userService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("games", gamesService.findAllPaging(0,9));

        if (user != null){
            model.addAttribute("steammerUserName", user.getName());
            model.addAttribute("steammerUserId",userService.findByUserId(user.getEmail()));
            System.out.println("userName"+user.getName());
        }
        return "index";
    }
}
