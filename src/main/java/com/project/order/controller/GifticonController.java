package com.project.order.controller;

import com.project.member.entity.Member;
import com.project.member.service.MemberService;
import com.project.order.dto.GifticonDTO;
import com.project.order.dto.OrderDTO;
import com.project.order.service.GifticonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/gifticon")
@Controller
public class GifticonController { //기프티콘

    @Autowired
    private GifticonService gifticonService;

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "/list") //기프티콘 리스트
    public String getGiftList(Model model, Authentication authentication, @PageableDefault(size=10, sort = "giftNum", direction = Sort.Direction.DESC) Pageable pageable){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Member member = memberService.getMember(userDetails.getUsername());
        Page<GifticonDTO> gifticons = gifticonService.getGifticonListPage(member.getId(), pageable);
        int startPage = Math.max(1,gifticons.getPageable().getPageNumber() - 4);
        int endPage = Math.min(gifticons.getTotalPages(),gifticons.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("gifticons", gifticons);
        return "order/myGifticon";
    }

}
