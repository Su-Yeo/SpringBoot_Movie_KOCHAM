package com.project.board.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_num; //이벤트 게시물 고유번호
    private String board_title; //이벤트 게시물 제목
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate board_start_date; //이벤트 시작날짜
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate board_end_date; //이벤트 종료날짜
    private String board_image; //이벤트 내용 이미지url
    private String board_thumbnail; //이벤트 썸네일 이미지 url
}
