package com.project.board.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardFormDto {
    private Long board_num; //이벤트 게시물 고유번호
    private String board_title; //이벤트 게시물 제목
    private LocalDateTime board_start_date; //이벤트 시작날짜
    private LocalDateTime board_end_date; //이벤트 종료날짜
    private String board_image; //이벤트 내용 이미지url
    private String board_thumbnail; //이벤트 썸네일 이미지 url
}
