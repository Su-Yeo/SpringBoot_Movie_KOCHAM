package com.project.movie.controller;

import com.project.member.dto.MemberFormDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/movie")
public class MovieController {

    // 영화 상영작 페이지
    @GetMapping(value = "/main")
    public String movieForm(Model model){

        return "movie/movieForm";
    }
    // 영화 상세정보 페이지
    @GetMapping(value = "/detail")
    public String detailPage(Model model){

        return "movie/detailPage";
    }

}
