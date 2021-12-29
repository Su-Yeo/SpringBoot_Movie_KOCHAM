package com.project.order.controller;

import com.project.order.dto.GifticonDTO;
import com.project.order.repository.GifticonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/gifticon")
@Controller
public class GifticonController { //기프티콘

    @Autowired
    private GifticonRepository gifticonRepository;

    @GetMapping(value = "/list") //임시
    public String getList(Model model){
        List<GifticonDTO> gifticons = gifticonRepository.findAll();
        model.addAttribute("gifticons", gifticons);
        return "order/gifticon";
    }

    @PostMapping(value = "/order") //임시
    public String getGifticon(@ModelAttribute GifticonDTO gifticon){
        gifticonRepository.save(gifticon);
        return "redirect:order/list";
    }
}
