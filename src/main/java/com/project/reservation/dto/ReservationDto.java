/*
package com.project.reservation.dto;


import com.project.reservation.entity.Schedule;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import java.time.LocalDateTime;


@Getter
@Setter
public class ReservationDto {

    private int movie_num;
    private int theater_area_num;

    private int schedule_start;
    private int schedule_end;
    private int schedule_cost;

    public Object toEntity() {
        return "";
    }




    public ReservationDto(Schedule entity) {

        this.movie_num = entity.movie_num();
        this.theater_area_num = entity.theater_area_num();
        this.schedule_start = entity.schedule_start();
        this.schedule_end = entity.schedule_end();
        this.schedule_cost = entity.schedule_cost();


    }
}
*/
