package com.project.admin.schedule.entity;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.sql.Date;

@Entity
@Table(name="schedule")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Schedule {

        @Id
        @Column(name="schedule_num")
        @GeneratedValue(strategy =  GenerationType.AUTO)
        private Long schedule_num ;

        /*@JoinColumn(name = "movie_num", referencedColumnName = "movie_num")*/
        @Column(nullable = false)
        private int movie_num;
        /*@JoinColumn(name = "theater_area_num", referencedColumnName = "theater_area_num")*/
        @Column(nullable = false)
        private int theater_area_num;

        @Column(nullable=false)
        @UpdateTimestamp
        private LocalDateTime schedule_date = LocalDateTime.now();

        @Column(nullable = false, length = 11)
        private Date schedule_start;
        @Column(nullable = false, length = 11)
        private Date schedule_end;
        @Column(nullable = false, length = 11)
        private int schedule_cost;

        @Column
        private String file_name;
        @Column
        private long file_size;

        @Builder
        public Schedule(int movie_num, int theater_area_num, LocalDateTime schedule_date,
                        Date schedule_start, Date schedule_end, int schedule_cost, String file_name, long file_size) {
                this.movie_num = movie_num;
                this.theater_area_num = theater_area_num;
                this.schedule_date = schedule_date;
                this.schedule_start = schedule_start;
                this.schedule_end = schedule_end;
                this.schedule_cost = schedule_cost;
                this.file_name = file_name;
                this.file_size = file_size;
        }

        public void update(int movie_num, int theater_area_num, LocalDateTime schedule_date,
                           Date schedule_start, Date schedule_end, int schedule_cost, String file_name, long file_size) {
                this.movie_num = movie_num;
                this.theater_area_num = theater_area_num;
                this.schedule_date = schedule_date;
                this.schedule_start = schedule_start;
                this.schedule_end = schedule_end;
                this.schedule_cost = schedule_cost;
                this.file_name = file_name;
                this.file_size = file_size;
        }





    }
