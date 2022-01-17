package com.project.admin.schedule.entity;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.convert.JodaTimeConverters;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;

@Entity
@Table(name="Schedule")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Schedule {

        @Id
        @Column(name="schedule_num")
        @GeneratedValue(strategy =  GenerationType.IDENTITY)
        private Long schedule_num ;

        /*@JoinColumn(name = "movie_num", referencedColumnName = "movie_num")*/
        @Column(nullable = false)
        private int movie_num;
        /*@JoinColumn(name = "theater_area_num", referencedColumnName = "theater_area_num")*/

        @Column(nullable = false)
        private int theater_area_num;

        @Column(nullable=false )
        private Date schedule_date  ;

        @Column(nullable = false, length = 11)
        private int schedule_start;
        @Column(nullable = false, length = 11)
        private int schedule_end;
        @Column(nullable = false, length = 11)
        private int schedule_cost;

        @Column
        private String file_name;
        @Column
        private long file_size;

        //@ManyToOne(fetch = FetchType.LAZY) // 일단 쓰겠다고 하는데 천천히 긁어 온다 ****연관관계 매핑 jpa
        //@JoinColumn(name="item_id") //조인할 대상 이름
        //private Item item_id; //조인할 칼럼있는 엔티티


        @Builder
        public Schedule(int movie_num, int theater_area_num, Date schedule_date,
                        int schedule_start, int schedule_end, int schedule_cost, String file_name, long file_size) {
                this.movie_num = movie_num;
                this.theater_area_num = theater_area_num;
                this.schedule_date = schedule_date;
                this.schedule_start = schedule_start;
                this.schedule_end = schedule_end;
                this.schedule_cost = schedule_cost;
                this.file_name = file_name;
                this.file_size = file_size;
        }

        public void update(int movie_num, int theater_area_num, Date schedule_date,
                           int schedule_start, int schedule_end, int schedule_cost, String file_name, long file_size) {
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
