
package com.project.admin.schedule.dto;

import com.project.admin.schedule.entity.Schedule;
import lombok.*;

import java.sql.Date;


@Getter
@Setter
@ToString
@NoArgsConstructor

public class ScheduleDTO {
    private Long schedule_num;
    private int movie_num =0;
    private int theater_area_num =0;

    private int schedule_start=0;
    private int schedule_end=0;
    private int schedule_cost =9000;

    private Date schedule_date ;

    private String file_name="";
    private long file_size=1;



    public Schedule toEntity( ) {
        return Schedule.builder()
            .schedule_date(schedule_date)
            .movie_num(movie_num)
            .theater_area_num(theater_area_num)
            .schedule_start(schedule_start)
            .schedule_end(schedule_end)
            .schedule_cost(schedule_cost)
            .file_name(file_name)
            .file_size(file_size)
            .build();
    }
    public ScheduleDTO(Schedule schedule) {
        this.schedule_num=schedule.getSchedule_num();
        this.movie_num = schedule.getMovie_num();
        this.theater_area_num = schedule.getTheater_area_num();
        this.schedule_start = schedule.getSchedule_start();
        this.schedule_end = schedule.getSchedule_end();
        this.schedule_cost = schedule.getSchedule_cost();
        this.file_name = schedule.getFile_name();
        this.file_size = schedule.getFile_size();
        this.schedule_date = schedule.getSchedule_date();

    }


}

