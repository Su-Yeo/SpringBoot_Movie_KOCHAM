package com.project.member.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "hell")
@Getter
@Setter
@ToString
public class Hall {

    @Id
    @Column(name="hall_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int hall_num;        //관람관 코드


    @ManyToOne
    @JoinColumn(name = "theater_area_num",nullable = false)
    private Theather theater_area_num;   // 영화관 지점코드


    @Column(nullable = false,name="hall_seatCount")
    private int hall_seatCount;       //관람관 좌석수

}
