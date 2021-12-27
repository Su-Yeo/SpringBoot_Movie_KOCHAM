package com.project.admin.controller;

import com.project.board.dto.Board;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

    @GetMapping(value = "/main") //관리자 메인 페이지
    public String adminMain( Model model){

        return "admin/adminMain";
    }

    @GetMapping(value = "/schedule") //상영스케줄 관리
    public String adminSchedule( Model model){

        return "admin/adminSchedule";
    }


    @GetMapping(value = "/member") //회원
    public String adminMember( Model model){

        return "admin/adminMember";
    }

    @GetMapping(value = "/movie") //영화
    public String adminMovie( Model model){

        return "admin/adminMovie";
    }

    @GetMapping(value = "/event") //이벤트
    public String adminEvent(@ModelAttribute Board board){

        return "admin/adminEvent";
    }



}
