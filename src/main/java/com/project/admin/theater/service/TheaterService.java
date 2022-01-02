
package com.project.admin.theater.service;

import com.project.admin.schedule.dto.ScheduleDTO;
import com.project.admin.schedule.entity.Schedule;
import com.project.admin.theater.dto.TheaterDTO;
import com.project.admin.theater.entity.Theater;
import com.project.admin.theater.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor//클래스 내에 final로 선언된 모든 멤버에 대한 생성자를 생성
public class TheaterService {

        private final TheaterRepository theaterRepository;



        //게시글 등록
        @Transactional
        public Long save(final TheaterDTO dto) {
            Theater entity = theaterRepository.save(dto.toEntity());

            return entity.getTheater_area_num();

        }

        //게시물 목록 보기
        public List<TheaterDTO> findAll(){


            /*Sort sort = Sort.by(Direction.DESC,"schedule_num");
            List<Schedule> list = scheduleRepository.findAll(sort);*/
            List<Theater> list = theaterRepository.findAll();
            return list.stream().map(TheaterDTO::new).collect(Collectors.toList());
        }

        //게시물 내용 보기
        @Transactional
        public TheaterDTO getView(Long theater_area_num) {

            Theater entity = theaterRepository.findById(theater_area_num).get();
            return new TheaterDTO(entity);
        }

        //게시물 수정
        @Transactional
        public Long update(final Long theater_area_num, final TheaterDTO dto) {

            Theater entity = theaterRepository.findById(theater_area_num).orElseThrow(()->new IllegalArgumentException("해당 게시물이 없습니다."));
            /*public void update(int movie_num, int theater_area_num, Date schedule_date,
            int schedule_start, int schedule_end, int schedule_cost, String file_name, long file_size) */
            entity.update(dto.getTheater_area_num(), dto.getTheater_name(),dto.getTheater_loc(),dto.getTheater_totalseat());
            /*entity.update(dto.getMovie_num(), dto.getTheater_area_num(),
                    dto.getSchedule_start(), dto.getSchedule_end(), dto.getschedule_date(), dto.getSchedule_cost(), dto.getFile_name(), dto.getFile_size());*/

            return theater_area_num;
        }

        //게시물 삭제
        @Transactional
        public void delete(final Long theater_area_num) {

            theaterRepository.deleteById(theater_area_num);
        }
}

