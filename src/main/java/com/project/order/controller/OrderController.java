package com.project.order.controller;

import com.project.order.dto.GifticonDTO;
import com.project.order.dto.OrderDTO;
import com.project.order.repository.GifticonRepository;
import com.project.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/order")
@Controller
public class OrderController { //주문 결제

    @Autowired
    private GifticonRepository gifticonRepository;

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/store") //임시
    public String getStore(){
        return "pay/storePay";
    }

    @GetMapping(value = "/gifticon") //임시
    public String getGifticon(){
        return "order/gifticon";
    }

    @GetMapping(value = "/gifticonOrder") //기프티콘 주문 페이지
    public String getGifticonOrder(Model model){
        List<GifticonDTO> gifticons = gifticonRepository.findAll();
        model.addAttribute("gifticons", gifticons);
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
