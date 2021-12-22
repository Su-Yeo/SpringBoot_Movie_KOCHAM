package com.project.reservation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/reservation")
public class ReservationController {

    @GetMapping(value = "/movie1")
    public String reservationMovie( Model model){


        model.addAttribute("data", "타임리프 예제 입니다.");
        return "reservation/movie1";
    }


}
