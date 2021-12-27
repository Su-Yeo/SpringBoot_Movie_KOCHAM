
package com.project.admin.schedule.dto;

import com.project.admin.schedule.entity.Schedule;
import lombok.*;

import java.time.LocalDateTime;
import java.sql.Date;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class ScheduleDTO {
    private int movie_num =0;
    private int theater_area_num =0;

    private Date schedule_start;
    private Date schedule_end;
    private int schedule_cost =9000;
    private LocalDateTime schedule_date = LocalDateTime.now();

    private String file_name="";
    private long file_size=1;

    /*public ScheduleDTO(int movie_num, int theater_area_num, Date schedule_start, Date schedule_end, int schedule_cost, LocalDateTime schedule_date, String file_name, long file_size) {
        this.movie_num = movie_num;
        this.theater_area_num = theater_area_num;
        this.schedule_start = schedule_start;
        this.schedule_end = schedule_end;
        this.schedule_cost = schedule_cost;
        this.schedule_date = schedule_date;
        this.file_name = file_name;
        this.file_size = file_size;
    }*/

    public Schedule toEntity( ) {
        return Schedule.builder()
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
        this.movie_num = schedule.getMovie_num();
        this.theater_area_num = schedule.getTheater_area_num();
        this.schedule_start = schedule.getSchedule_start();
        this.schedule_end = schedule.getSchedule_end();
        this.schedule_cost = schedule.getSchedule_cost();
        this.file_name = schedule.getFile_name();
        this.file_size = schedule.getFile_size();

    }
}

