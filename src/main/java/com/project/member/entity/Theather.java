package com.project.member.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "theather")
@Getter
@Setter
@ToString
public class Theather {

    @Id
    @Column(name="theater_area_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int theater_area_num;        //영화관 지점코드

    @Column(nullable = false, length = 50)
    private String theater_name;   //영화관 지점명

    @Column(nullable = false, length = 200)
    private String theater_loc;       //영화관 지역

}
