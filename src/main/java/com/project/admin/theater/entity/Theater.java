package com.project.admin.theater.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="theater")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Theater {

        @Id
        @Column(name="theater_area_num")
        @GeneratedValue(strategy =  GenerationType.IDENTITY)
        private Long theater_area_num;

        @Column
        private String theater_name;
        @Column
        private String theater_loc;
        @Column
        private int theater_totalseat;

        //@ManyToOne(fetch = FetchType.LAZY) // 일단 쓰겠다고 하는데 천천히 긁어 온다 ****연관관계 매핑 jpa
        //@JoinColumn(name="item_id") //조인할 대상 이름
        //private Item item_id; //조인할 칼럼있는 엔티티


        @Builder
        public Theater(Long theater_area_num, String theater_name, String theater_loc, int theater_totalseat) {
                this.theater_area_num = theater_area_num;
                this.theater_name = theater_name;
                this.theater_loc = theater_loc;
                this.theater_totalseat = theater_totalseat;
        }

        public void update(Long theater_area_num, String theater_name, String theater_loc, int theater_totalseat) {
                this.theater_area_num = theater_area_num;
                this.theater_name = theater_name;
                this.theater_loc = theater_loc;
                this.theater_totalseat = theater_totalseat;
        }





    }
