package com.project.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user-find")
public class IdController {

    @GetMapping
    public String modify(){
        return "modify/user-find";
    }

}
