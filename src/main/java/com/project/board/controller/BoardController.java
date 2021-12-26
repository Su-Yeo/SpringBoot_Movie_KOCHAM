package com.project.board.controller;

import com.project.board.dto.Board;
import com.project.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/board")
@Controller
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping(value = "/main")
    public String startEvent(){
        return "board/boardSlide";
    }

    @PostMapping(value = "/write")
    public String writeEvent(@ModelAttribute Board board){
        boardRepository.save(board);
        return "board/boardSlide";
    }
}
