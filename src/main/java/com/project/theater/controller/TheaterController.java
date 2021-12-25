package com.project.theater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/theater")
public class TheaterController {

    @GetMapping(value = "/main")
    public String memberForm(Model model){

        return "theater/theaterMain";
    }
}
