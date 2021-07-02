package com.steammer.web.dto;

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
        model.addAttribute("games", gamesService.findAllDesc());
        return "index";
    }
}
