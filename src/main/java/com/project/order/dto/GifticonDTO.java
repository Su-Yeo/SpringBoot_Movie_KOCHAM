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
    @Column(nullable = false, name="gift_num")
    private Long giftNum; //기프티콘코드
    @Column(nullable = false)
    private String member_id; //아이디(구매자)
    @Column(nullable = false)
    private Long product_num; //상품코드
    @Column(nullable = false)
    private String product_name; //상품명
    @Column(nullable = false)
    private String order_from_id; //보내신분
    @Column(nullable = false)
    private LocalDateTime gift_exp; //유효기간
    @Column(nullable = false)
    private LocalDateTime order_date; //받은 날짜
    @Column(nullable = false)
    private int product_price; //기프티콘금액
}