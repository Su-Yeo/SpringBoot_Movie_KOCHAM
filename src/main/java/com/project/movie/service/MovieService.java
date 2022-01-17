package com.project.movie.service;


import com.project.movie.dto.MovieDTO;
import com.project.movie.entity.Movie;
import com.project.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.ServiceMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    //게시글 등록
    @Transactional
    public Long save(final MovieDTO dto){
        Movie entity = movieRepository.save(dto.toEntity());

        return entity.getMovie_num();
    }

    //게시물 목록 보기
    public List<MovieDTO> findAll(){
        List<Movie> list = movieRepository.findAll();
        return list.stream().map(MovieDTO::new).collect(Collectors.toList());
    }

    //게시물 내용 보기
    @Transactional
    public MovieDTO getView(Long movie_num) {

        Movie entity = movieRepository.findById(movie_num).get();
        return new MovieDTO(entity);
    }

    //게시물 수정
    @Transactional
    public Long update(final Long movie_num, final MovieDTO dto) {

        Movie entity = movieRepository.findById(movie_num).orElseThrow(()->new IllegalArgumentException("해당 게시물이 없습니다."));
            /*public void update(int movie_num, int theater_area_num, Date schedule_date,
            int schedule_start, int schedule_end, int schedule_cost, String file_name, long file_size) */
        entity.update(dto.getMovie_title(), dto.getMovie_openningDate(), dto.getMovie_closingDate(),
                dto.getMovie_ageClass(), dto.getMovie_grade(), dto.getMovie_ratio(), dto.getMovie_image(), dto.getMovie_desc(), dto.getMovie_run());
            /*entity.update(dto.getMovie_num(), dto.getTheater_area_num(),
                    dto.getSchedule_start(), dto.getSchedule_end(), dto.getschedule_date(), dto.getSchedule_cost(), dto.getFile_name(), dto.getFile_size());*/

        return movie_num;
    }

    //게시물 삭제
    @Transactional
    public void delete(final Long movie_num) {

        movieRepository.deleteById(movie_num);
    }

}
