/*
package com.project.reservation.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="reservation")
@Getter
@Setter
@ToString
public class Reservation {

        @Id
        @Column(name="schedule_num")
        @GeneratedValue(strategy =  GenerationType.AUTO)
        private Long id;

        @JoinColumn(name = "movie_num", referencedColumnName = "movie_num")
        private int movie_num;
        @JoinColumn(name = "theater_area_num", referencedColumnName = "theater_area_num")
        private int theater_area_num;

        @Column(nullable=false)
        @UpdateTimestamp
        private LocalDateTime schedule_date = LocalDateTime.now();

        @Column(nullable = false, length = 11)
        private int scheduleStart;
        @Column(nullable = false, length = 11)
        private int scheduleEnd;
        @Column(nullable = false, length = 11)
        private int scheduleCost;





    }
*/
