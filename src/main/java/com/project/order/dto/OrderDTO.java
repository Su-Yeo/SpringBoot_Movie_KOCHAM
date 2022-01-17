package com.project.order.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="order_pay")
@Getter
@Setter
public class OrderDTO { //주문 결제 테이블
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(nullable = false, name = "order_num")
    private Long orderNum; //판매코드
    @Column(nullable = false, name = "member_id")
    private Long memberId; //아이디(구매자)
    private String product_name; //상품명
    private Long product_num; //상품코드(스토어/영화)
    private int order_total_price; //가격
    private LocalDateTime order_date; //주문날짜
    private int order_num_group; //주문번호
    private String order_to_id; //받으시는 분
    private int order_discount; //기프티콘 할인
    private int order_origin_price; //원금
    private int order_plus_point; //적립금
    private int order_minus_point; //포인트 할인
    private String order_type; //분류(스토어/영화)

}