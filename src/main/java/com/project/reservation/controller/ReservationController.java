package com.project.reservation.controller;

import com.project.member.dto.BookingDto;
import groovy.util.logging.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Log4j
@Controller
@RequestMapping(value="/movie/screening")
public class ReservationController {

//    @GetMapping("/booking")
//    public String doBooking(@RequestParam(value = "movie_num", required = false) int movie_num, Model model)
//            throws Exception {
//
//        System.out.println("##################################movie_num:"+movie_num);
//        BookingDto dto = new BookingDto();
//        model.addAttribute("movieRead", s2.movieRead(movie_num));
//
//        System.out.println("##################################movie_num:"+s2.movieRead(movie_num));
//
//        // 영화정보 페이지에서 예약버튼을 누르면 해당 영화번호를 받아와서 set으로 설정한다.
//        dto.setMovieNum(movie_num);
//
//        List<String> list = service.getCities(dto);
//        model.addAttribute("list", list); // 여기까지 했고 jsp에 for-each문 생각해보자
//
//        return "reserve";
//    }// doBooking









    @GetMapping(value = "/choiceMovie")
    public String reservationMovie( Model model){


        model.addAttribute("data", "타임리프 예제 입니다.");
        return "reservation/movieTicket";
    }


}
