package com.project.board.controller;

import com.project.admin.schedule.dto.ScheduleDTO;
import com.project.board.dto.Board;
import com.project.board.repository.BoardRepository;
import com.project.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/board")
@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping(value = "/main")
    public String startEvent(Model model){
        List<Board> boards = boardService.boardList();
        model.addAttribute("boards", boards);
        return "board/boardSlide";
    }

    @GetMapping(value = "/content")
    public String boardContent(Model model, @RequestParam(name="board_num", required = true)Long board_num){
        Board board = boardService.boardContent(board_num);
        model.addAttribute("board", board);
        return "board/boardContent";
    }

    @PostMapping(value = "/write")
    public String writeEvent(@ModelAttribute Board board){
        boardService.saveBoard(board);
        return "redirect:/board/main";
    }

    //이벤트 게시판 내용 수정
    @GetMapping(value = "/modify")
    public String getMModify(Model model, @RequestParam(name="board_num", required=false) Long board_num) throws Exception {
        Board board = boardService.boardContent(board_num);
        model.addAttribute("board", board);
        return "admin/adminEvent";
    }

    //이벤트 게시판 내용 수정
    @PostMapping(value = "/modify")
    public String postMModify(@ModelAttribute Board board, @RequestParam(name="board_num", required=false) Long board_num) throws Exception {
        boardService.updateBoard(board, board_num);
        return "redirect:/board/main";
    }

    //이벤트 게시판 내용 삭제
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String postDelete(@RequestParam(name="board_num", required=false) Long board_num) throws Exception {

        boardService.deleteBoard(board_num);
        return "redirect:/board/main";

    }
}
