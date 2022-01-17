package com.project.order.controller;

import com.project.member.entity.Member;
import com.project.member.service.MemberService;
import com.project.order.dto.GifticonDTO;
import com.project.order.dto.OrderDTO;
import com.project.order.service.GifticonService;
import com.project.order.service.OrderService;
import com.project.reservation.movie.dto.MovieDTO;
import com.project.reservation.movie.dto.ReservationMovieDTO;
import com.project.store.dto.ItemDto;
import com.project.store.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @PostMapping(value = "/store") //상품 주문 페이지
    public String getStoreOrder(Model model, ItemDto itemDto, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Member member = memberService.getMember(userDetails.getUsername());
        model.addAttribute("member",member);
        model.addAttribute("item", itemDto);
        model.addAttribute("order",new OrderDTO());
        return "order/storeOrder";
    }

    @PostMapping(value = "/movie") //영화 주문 페이지
    public String getMovieOrder(Model model, ReservationMovieDTO reservationMovieDTO, MovieDTO movieDTO, Authentication authentication, HttpServletRequest request) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Member member = memberService.getMember(userDetails.getUsername());
        List<GifticonDTO> gifticons = gifticonService.getGifticonList(member.getId());
        model.addAttribute("member",member);
        model.addAttribute("gifticons", gifticons);
        model.addAttribute("movie", reservationMovieDTO);
        model.addAttribute("movieDTO", movieDTO);
        model.addAttribute("order",new OrderDTO());
        return "order/movieOrder";
    }

    @PostMapping(value="storePay") //스토어결제
    public String postStorePay(@ModelAttribute OrderDTO orderPay, HttpServletRequest request){
        orderService.saveOrder(orderPay);
        memberService.setPlusPoint(orderPay.getMemberId(), orderPay.getOrder_plus_point());
        if(orderPay.getOrder_minus_point()>0){
            memberService.setMinusPoint(orderPay.getMemberId(),orderPay.getOrder_minus_point());
        }
        //수량 만큼 기프티콘 생성하기
        int count = Integer.parseInt(request.getParameter("count"));
        for (int i=0; i<count; i++){
            gifticonService.saveGifticon(orderPay, orderPay.getOrder_origin_price()/count);
        }
        return "redirect:/gifticon/list";
    }

    @PostMapping(value="moviePay") //영화결제
    public String postMoviePay(@ModelAttribute OrderDTO orderPay, HttpServletRequest request){
        orderService.saveOrder(orderPay);
        memberService.setPlusPoint(orderPay.getMemberId(), orderPay.getOrder_plus_point());
        if(orderPay.getOrder_minus_point()>0){
            memberService.setMinusPoint(orderPay.getMemberId(),orderPay.getOrder_minus_point());
        }
        if (request.getParameter("giftNum") != null){
            Long giftNum = Long.valueOf(request.getParameter("giftNum"));
            gifticonService.deleteGifticon(giftNum);
        }
        return "redirect:/order/list";
    }

    @GetMapping(value = "/list") //구매내역
    public String getOrderList(Model model, Authentication authentication, @PageableDefault(size=10, sort = "orderNum", direction = Sort.Direction.DESC) Pageable pageable){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Member member = memberService.getMember(userDetails.getUsername());
        Page<OrderDTO> orders = orderService.findOrder(member.getId(), pageable);
        int startPage = Math.max(1,orders.getPageable().getPageNumber() - 4);
        int endPage = Math.min(orders.getTotalPages(),orders.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("orders", orders);
        return "order/myOrder";
    }

}
