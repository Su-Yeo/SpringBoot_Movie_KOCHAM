package com.project.order.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="gifticon")
@Getter
@Setter
public class GifticonDTO { //기프티콘 테이블
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long gift_num; //키프티콘코드
    private String member_id; //아이디(구매자)
    private Long product_num; //상품코드
    private String product_name; //상품명
    private String order_from_id; //보내신분
    private LocalDateTime gift_exp; //유효기간
    private LocalDateTime order_date; //받은 날짜

}