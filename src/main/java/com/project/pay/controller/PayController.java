package com.project.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pay")
@Controller
public class PayController {

    @GetMapping(value = "/store")
    public String getStore(){
        return "pay/storePay";
    }

    @GetMapping(value = "/gifticon")
    public String getGifticon(){
        return "pay/gifticon";
    }
}
