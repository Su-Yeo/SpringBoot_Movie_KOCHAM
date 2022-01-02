
package com.project.admin.theater.dto;

import com.project.admin.theater.entity.Theater;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;


@Getter
@Setter
@ToString
@NoArgsConstructor

public class TheaterDTO {
    private Long theater_area_num;
    private String theater_name;
    private String theater_loc;
    private int theater_totalseat;


    public Theater toEntity( ) {
        return Theater.builder()
            .theater_name(theater_name)
            .theater_loc(theater_loc)
            .theater_area_num(theater_area_num)
                .theater_totalseat(theater_totalseat)
            .build();
    }
    public TheaterDTO(Theater theater) {
        this.theater_area_num=theater.getTheater_area_num();
        this.theater_name=theater.getTheater_name();
        this.theater_loc=theater.getTheater_loc();
        this.theater_totalseat=theater.getTheater_totalseat();

    }


}

