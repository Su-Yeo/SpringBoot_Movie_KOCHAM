package com.project.board.service;

import com.project.board.dto.Board;
import com.project.board.repository.BoardRepository;
import com.project.member.entity.Member;
import com.project.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> boardList(){
        //Sort sort = Sort.by(Sort.Direction.DESC, "board_num");
        List<Board> boards = boardRepository.findAll();
        return boards; //.stream().map(Board::new).collect(Collectors.toList());
    }

    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    public Board updateBoard(Board board2, Long board_num) {
        Board board = boardRepository.findById(board_num).orElseThrow(EntityNotFoundException::new);;
        board.updateBoard(board2.getBoard_title(), board2.getBoard_start_date(), board2.getBoard_end_date(), board2.getBoard_image(), board2.getBoard_thumbnail());
        return boardRepository.save(board);
    }

    public void deleteBoard(Long board_num) {
        boardRepository.deleteById(board_num);
    }

    @Transactional(readOnly = true)
    public Board boardContent(Long board_num) {
        Board board = boardRepository.findById(board_num).orElseThrow(EntityNotFoundException::new);
        return board;
    }
}
