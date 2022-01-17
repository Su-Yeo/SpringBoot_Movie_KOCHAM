
package com.project.reservation.movie.dto;

import com.project.reservation.movie.entity.ReservationMovie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;


@Getter
@Setter
@ToString
@NoArgsConstructor

public class ReservationMovieDTO {


    private Long ticket_num ;
    private int seat_num ;
    private int ticket_group ;
    private int movie_num ;
    private int schedule_num ;
    private int theater_area_num ;
    private String member_id ;
    private Date ticket_date ;
    private String theater_name;
    private String theater_city;
    private String theater_loc;
    private int theater_totalSeat;
    private int schedule_start;
    private int schedule_end;
    private Date schedule_date ;



    public ReservationMovie toEntity( ) {
        return ReservationMovie.builder()
            .ticket_num(ticket_num)
            .seat_num(seat_num)
            .movie_num(movie_num)
            .schedule_num(schedule_num)
            .theater_area_num(theater_area_num)
            .member_id(member_id)
            .ticket_date(ticket_date)
            .theater_name(theater_name)
            .theater_city(theater_city)
            .theater_loc(theater_loc)
            .theater_totalSeat(theater_totalSeat)
            .schedule_start(schedule_start)
            .schedule_end(schedule_end)
            .schedule_date(schedule_date)

            .build();
    }
    public ReservationMovieDTO(ReservationMovie RM) {
        this.ticket_num=RM.getTicket_num();
        this.seat_num = RM.getSeat_num();
        this.movie_num = RM.getMovie_num();
        this.schedule_num = RM.getSchedule_num();
        this.theater_area_num = RM.getTheater_area_num();
        this.member_id = RM.getMember_id();
        this.ticket_date = RM.getTicket_date();
        this.theater_name = RM.getTheater_name();
        this.theater_city = RM.getTheater_city();
        this.theater_loc = RM.getTheater_loc();
        this.theater_totalSeat = RM.getTheater_totalSeat();
        this.schedule_start = RM.getSchedule_start();
        this.schedule_end = RM.getSchedule_end();
        this.schedule_date = RM.getSchedule_date();

    }


}

