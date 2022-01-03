package com.project.movie.dto;

import com.project.movie.entity.Movie;
import com.project.store.dto.ItemImgDto;
import lombok.*;


import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class MovieDTO {
    private Long movie_num;
    private String movie_title;
    private Date movie_openningDate;
    private Date movie_closingDate;
    private int movie_ageClass;     // 시청연련제한
    private double movie_grade;     // 평점
    private double movie_ratio;     // 예매율
    private String movie_image;
    private String movie_desc;        // 줄거리
    private int movie_run;

    private List<MovieImgDto> movieImgDtoList = new ArrayList<>(); // 234p 1. 상품 저장 후 수정할 때 상품 이미지 정보를 저장하는 리스트 입니다.

    private List<Long> movieImgIds = new ArrayList<>(); // 2. 상품의 미미지 아이디를 저장하는 리스트입니다. 상품 등록 시에는 아직 상품의 이미지를 저장하지 않았기 때문에
    // 아무 값도 들어가 있지 않고 수정 시에 이미지 아이디를 담아둘 용도로 사용합니다.

    public Movie toEntity() {
        return Movie.builder()
                .movie_num(movie_num)
                .movie_title(movie_title)
                .movie_openningDate(movie_openningDate)
                .movie_closingDate(movie_closingDate)
                .movie_ageClass(movie_ageClass)
                .movie_grade(movie_grade)
                .movie_ratio(movie_ratio)
                .movie_image(movie_image)
                .movie_desc(movie_desc)
                .movie_run(movie_run)
                .build();
    }
    public MovieDTO(Movie movie) {
            this.movie_num = movie.getMovie_num();
            this.movie_title = movie.getMovie_title();
            this.movie_openningDate = movie.getMovie_openningDate();
            this.movie_closingDate = movie.getMovie_closingDate();
            this.movie_ageClass = movie.getMovie_ageClass();
            this.movie_grade = movie.getMovie_grade();
            this.movie_ratio = movie.getMovie_ratio();
            this.movie_image = movie.getMovie_image();
            this.movie_desc = movie.getMovie_desc();
            this.movie_run = movie.getMovie_run();
    }
}
