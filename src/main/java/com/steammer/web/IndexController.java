package com.steammer.web;

import com.steammer.service.GamesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final GamesService gamesService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("games", gamesService.findAllPaging(0,9));
        return "index";
    }
}
