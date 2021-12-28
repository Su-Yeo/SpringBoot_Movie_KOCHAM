package com.project.store.entity;


import com.project.store.constant.ItemSellStatus;
import com.project.store.constant.ProductTypeStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
public class Product {

    @Id
    @Column(name="product_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long product_num;        //상품코드

    @Column(nullable = false, length = 50)
    private String product_name;   //상품명

    @Column(name = "product_price", nullable = false)
    private int product_price;       //가격

    @Column(name = "product_discount", nullable = false)
    private int product_discount;       //할인율

    @Enumerated(EnumType.STRING)
    private ProductTypeStatus product_type; //상품 종류

    @Enumerated(EnumType.STRING)
    private ItemSellStatus product_sale; //상품 판매 여부

    @Column(nullable = false)
    private int product_stock; //재고수량

    @Lob
    @Column(nullable = false)
    private  String product_desc; //상품 상세 설명





    private LocalDateTime product_date; //등록 시간



}