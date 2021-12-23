package com.project.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pass-find")
public class PwController {

    @GetMapping
    public String modify() {
        return "modify/pass-find";
    }

}
