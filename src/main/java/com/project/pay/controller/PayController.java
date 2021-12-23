package com.project.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pay")
@Controller
public class PayController {

    @GetMapping(value = "/store")
    public String startHome(){
        return "pay/storePay";
    }
}
