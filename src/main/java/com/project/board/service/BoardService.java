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
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> boardList(){
        //List<Board> boards = boardRepository.findAll(Sort.by(Sort.Direction.DESC,"board_num"));
        List<Board> boards = boardRepository.findAll();
        return boards;
    }

    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Board boardContent(Long board_num) {
        Board board = boardRepository.findById(board_num).orElseThrow(EntityNotFoundException::new);
        return board;
    }
}
