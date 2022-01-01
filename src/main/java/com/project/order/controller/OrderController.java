package com.project.order.controller;

import com.project.member.dto.MemberFormDto;
import com.project.member.entity.Member;
import com.project.member.service.MemberService;
import com.project.order.dto.GifticonDTO;
import com.project.order.dto.OrderDTO;
import com.project.order.repository.GifticonRepository;
import com.project.order.service.GifticonService;
import com.project.order.service.OrderService;
import com.project.store.dto.ItemDto;
import com.project.store.dto.ItemFormDto;
import com.project.store.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RequestMapping("/order")
@Controller
public class OrderController { //주문 결제

    @Autowired
    private GifticonService gifticonService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "/store") //임시
    public String getStore(){
        return "order/storePay";
    }

    @GetMapping(value = "/gifticon") //임시
    public String getGifticon(){
        return "order/gifticon";
    }

    @PostMapping(value = "/gifticonOrder") //기프티콘 주문 페이지
    public String getGifticonOrder(Model model, ItemDto itemDto, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Member member = memberService.getMember(userDetails.getUsername());
        List<GifticonDTO> gifticons = gifticonService.getGifticonList(member.getId());
        model.addAttribute("member",member);
        model.addAttribute("gifticons", gifticons);
        model.addAttribute("item", itemDto);
        model.addAttribute("order",new OrderDTO());
        return "order/gifticonOrder";
    }

    @PostMapping(value="pay") //결제
    public String postPay(@ModelAttribute OrderDTO orderPay){
        orderService.saveOrder(orderPay);
        return "redirect:/order/list";
    }

    @GetMapping(value = "/list") //구매내역
    public String getOrderList(Model model){
        List<OrderDTO> orders = orderService.findOrder();
        model.addAttribute("orders", orders);
        return "order/myOrder";
    }

}
