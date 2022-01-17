
package com.project.admin.schedule.service;

import com.project.admin.schedule.dto.ScheduleDTO;
import com.project.admin.schedule.entity.Schedule;
import com.project.admin.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor//클래스 내에 final로 선언된 모든 멤버에 대한 생성자를 생성
public class ScheduleService {

        private final ScheduleRepository scheduleRepository;



        //게시글 등록
        @Transactional
        public Long save(final ScheduleDTO dto) {
            Schedule entity = scheduleRepository.save(dto.toEntity());

            return entity.getSchedule_num();

        }

        //게시물 목록 보기
        public List<ScheduleDTO> findAll(){


            /*Sort sort = Sort.by(Direction.DESC,"schedule_num");
            List<Schedule> list = scheduleRepository.findAll(sort);*/
            List<Schedule> list = scheduleRepository.findAll();
            return list.stream().map(ScheduleDTO::new).collect(Collectors.toList());
        }

        //게시물 내용 보기
        @Transactional
        public ScheduleDTO getView(Long schedule_num) {

            Schedule entity = scheduleRepository.findById(schedule_num).get();
            return new ScheduleDTO(entity);
        }

        //게시물 수정
        @Transactional
        public Long update(final Long schedule_num, final ScheduleDTO dto) {

            Schedule entity = scheduleRepository.findById(schedule_num).orElseThrow(()->new IllegalArgumentException("해당 게시물이 없습니다."));
            /*public void update(int movie_num, int theater_area_num, Date schedule_date,
            int schedule_start, int schedule_end, int schedule_cost, String file_name, long file_size) */
            entity.update(dto.getMovie_num(), dto.getTheater_area_num(),dto.getSchedule_date(),dto.getSchedule_start(),
                     dto.getSchedule_end(),  dto.getSchedule_cost(), dto.getFile_name(), dto.getFile_size());
            /*entity.update(dto.getMovie_num(), dto.getTheater_area_num(),
                    dto.getSchedule_start(), dto.getSchedule_end(), dto.getschedule_date(), dto.getSchedule_cost(), dto.getFile_name(), dto.getFile_size());*/

            return schedule_num;
        }

        //게시물 삭제
        @Transactional
        public void delete(final Long schedule_num) {

            scheduleRepository.deleteById(schedule_num);
        }
}

