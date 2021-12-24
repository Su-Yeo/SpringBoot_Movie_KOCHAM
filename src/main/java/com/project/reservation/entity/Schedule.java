package com.project.reservation.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;
import sun.util.calendar.LocalGregorianCalendar;

import java.time.LocalDateTime;

@Entity
@Table(name="schedule")
@Getter
@Setter
@ToString
public class Schedule {

        @Id
        @Column(name="schedule_num")
        @GeneratedValue(strategy =  GenerationType.AUTO)
        private Long id;

        @JoinColumn(name = "movie_num", referencedColumnName = "movie_num")
        private int movie_num;
        @JoinColumn(name = "theater_area_num", referencedColumnName = "theater_area_num")
        private int theater_area_num;

        @Column
        @UpdateTimestamp
        private LocalDateTime scheduleDate;

        @Column(nullable = false, length = 11)
        private int scheduleStart;
        @Column(nullable = false, length = 11)
        private int scheduleEnd;
        @Column(nullable = false, length = 11)
        private int scheduleCost;





    }
