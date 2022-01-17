package com.project.reservation.movie.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="ticket")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReservationMovie {

        @Id
        @Column(name="ticket_num")
        @GeneratedValue(strategy =  GenerationType.IDENTITY)
        private Long ticket_num ;


        @Column(nullable = false)
        private int seat_num ;
        @Column(nullable = false)
        private int ticket_group ;
        @Column(nullable = false)
        private int movie_num ;
        @Column(nullable = false)
        private int schedule_num ;
        @Column(nullable = false)
        private int theater_area_num ;
        @Column(nullable = false)
        private String member_id ;
        @Column(nullable = false)
        private Date ticket_date ;
        @Column(nullable = false)
        private String theater_name;
        @Column(nullable = false)
        private String theater_city;
        @Column(nullable = false)
        private String theater_loc;
        @Column(nullable = false)
        private int theater_totalSeat;
        @Column(nullable = false)
        private int schedule_start;
        @Column(nullable = false)
        private int schedule_end;
        @Column(nullable=false )
        private Date schedule_date;


        //@ManyToOne(fetch = FetchType.LAZY) // 일단 쓰겠다고 하는데 천천히 긁어 온다 ****연관관계 매핑 jpa
        //@JoinColumn(name="item_id") //조인할 대상 이름
        //private Item item_id; //조인할 칼럼있는 엔티티


        @Builder


        public ReservationMovie(Long ticket_num, int seat_num, int ticket_group, int movie_num, int schedule_num, int theater_area_num, String member_id, Date ticket_date, String theater_name, String theater_city, String theater_loc, int theater_totalSeat, int schedule_start, int schedule_end, Date schedule_date) {
                this.ticket_num = ticket_num;
                this.seat_num = seat_num;
                this.ticket_group = ticket_group;
                this.movie_num = movie_num;
                this.schedule_num = schedule_num;
                this.theater_area_num = theater_area_num;
                this.member_id = member_id;
                this.ticket_date = ticket_date;
                this.theater_name = theater_name;
                this.theater_city = theater_city;
                this.theater_loc = theater_loc;
                this.theater_totalSeat = theater_totalSeat;
                this.schedule_start = schedule_start;
                this.schedule_end = schedule_end;
                this.schedule_date = schedule_date;
        }

        public void update(Long ticket_num, int seat_num, int ticket_group, int movie_num, int schedule_num, int theater_area_num,
                           String member_id, Date ticket_date, String theater_name, String theater_city, String theater_loc,
                           int theater_totalSeat, int schedule_start, int schedule_end, Date schedule_date) {
                this.ticket_num = ticket_num;
                this.seat_num = seat_num;
                this.ticket_group = ticket_group;
                this.movie_num = movie_num;
                this.schedule_num = schedule_num;
                this.theater_area_num = theater_area_num;
                this.member_id = member_id;
                this.ticket_date = ticket_date;
                this.theater_name = theater_name;
                this.theater_city = theater_city;
                this.theater_loc = theater_loc;
                this.theater_totalSeat = theater_totalSeat;
                this.schedule_start = schedule_start;
                this.schedule_end = schedule_end;
                this.schedule_date = schedule_date;


        }


}
