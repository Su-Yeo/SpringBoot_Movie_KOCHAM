package com.project.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/store")
public class storeController {

    @GetMapping(value = "/main")
    public String memberForm(Model model){

        return "store/storeMain";
    }
}
