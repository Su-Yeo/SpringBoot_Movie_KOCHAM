package com.project.order.controller;

import com.project.order.dto.GifticonDTO;
import com.project.order.repository.GifticonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String getList(Model model, @PageableDefault(size=2, sort = "giftNum", direction = Sort.Direction.DESC) Pageable pageable){
        Page<GifticonDTO> gifticons = gifticonRepository.findAll(pageable);
        int startPage = Math.max(1,gifticons.getPageable().getPageNumber() - 4);
        int endPage = Math.min(gifticons.getTotalPages(),gifticons.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("gifticons", gifticons);
        return "order/gifticon";
    }

    @PostMapping(value = "/order") //임시
    public String getGifticon(@ModelAttribute GifticonDTO gifticon){
        gifticonRepository.save(gifticon);
        return "redirect:order/list";
    }
}
