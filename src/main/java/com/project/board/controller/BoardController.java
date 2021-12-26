package com.project.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/board")
@Controller
public class BoardController {
    @GetMapping(value = "/main")
    public String startEvent(){
        return "board/boardSlide";
    }

    @PostMapping(value = "/write")
    public String writeEvent(){

        return "board/boardSlide";
    }
}
