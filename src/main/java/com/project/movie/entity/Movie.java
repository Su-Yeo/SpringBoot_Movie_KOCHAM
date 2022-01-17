package com.project.movie.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Date;

@Entity
@Table(name="movie")
@Getter
@Setter
@ToString
@NoArgsConstructor

public class Movie {
    @Id
    @Column(name="movie_num")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long movie_num;

    @Column(nullable = false)
    private String movie_title;

    @Column(nullable = false, length=11)
    private Date movie_openningDate;
    @Column(nullable = false, length=11)
    private Date movie_closingDate;

    @Column(nullable = false)
    private int movie_ageClass;

    @Column(nullable = false)
    private double movie_grade;
    @Column(nullable = false)
    private double movie_ratio;

    @Column(nullable = false)
    private String movie_image;

    @Column(nullable = false)
    private String movie_desc;

    @Column(nullable = false)
    private int movie_run;

    @Builder
    public Movie(Long movie_num, String movie_title, Date movie_openningDate, Date movie_closingDate,
                int movie_ageClass, double movie_grade, double movie_ratio, String movie_image, String movie_desc, int movie_run) {
        this.movie_num = movie_num;
        this.movie_title = movie_title;
        this.movie_openningDate = movie_openningDate;
        this.movie_closingDate = movie_closingDate;
        this.movie_ageClass = movie_ageClass;
        this.movie_grade = movie_grade;
        this.movie_ratio = movie_ratio;
        this.movie_image = movie_image;
        this.movie_desc = movie_desc;
        this.movie_run = movie_run;
    }
        public void update(String movie_title, Date movie_openningDate, Date movie_closingDate,
            int movie_ageClass, double movie_grade, double movie_ratio, String movie_image, String movie_desc, int movie_run) {
        this.movie_title = movie_title;
        this.movie_openningDate = movie_openningDate;
        this.movie_closingDate = movie_closingDate;
        this.movie_ageClass = movie_ageClass;
        this.movie_grade = movie_grade;
        this.movie_ratio = movie_ratio;
        this.movie_image = movie_image;
        this.movie_desc = movie_desc;
        this.movie_run = movie_run;
    }
}
