
package com.project.reservation.movie.service;

import com.project.reservation.movie.dto.ReservationMovieDTO;
import com.project.reservation.movie.entity.ReservationMovie;
import com.project.reservation.movie.repository.ReservationMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor//클래스 내에 final로 선언된 모든 멤버에 대한 생성자를 생성
public class ReservationMovieService {

        private final ReservationMovieRepository scheduleRepository;



        //게시글 등록
        @Transactional
        public Long save(final ReservationMovieDTO dto) {
            ReservationMovie entity = scheduleRepository.save(dto.toEntity());

            return entity.getTicket_num();

        }

        //게시물 목록 보기
        public List<ReservationMovieDTO> findAll(){


            /*Sort sort = Sort.by(Direction.DESC,"ticket_num");
            List<Schedule> list = scheduleRepository.findAll(sort);*/
            List<ReservationMovie> list = scheduleRepository.findAll();
            return list.stream().map(ReservationMovieDTO::new).collect(Collectors.toList());
        }

        //게시물 내용 보기
        @Transactional
        public ReservationMovieDTO getView(Long ticket_num) {

            ReservationMovie entity = scheduleRepository.findById(ticket_num).get();
            return new ReservationMovieDTO(entity);
        }

        //게시물 수정
        @Transactional
        public Long update(final Long ticket_num, final ReservationMovieDTO dto) {

            ReservationMovie entity = scheduleRepository.findById(ticket_num).orElseThrow(()->new IllegalArgumentException("해당 게시물이 없습니다."));
            /*public void update(int movie_num, int theater_area_num, Date schedule_date,
            int schedule_start, int schedule_end, int schedule_cost, String file_name, long file_size) */
            entity.update(dto.getTicket_num(),dto.getSeat_num(),dto.getTicket_group(),dto.getMovie_num(),dto.getSchedule_num(),
                    dto.getTheater_area_num(),dto.getMember_id(),dto.getTicket_date(),dto.getTheater_name(),dto.getTheater_city(),
                    dto.getTheater_loc(),dto.getTheater_totalSeat(), dto.getSchedule_start(),dto.getSchedule_end(),dto.getSchedule_date());
            /*entity.update(dto.getMovie_num(), dto.getTheater_area_num(),
                    dto.getSchedule_start(), dto.getSchedule_end(), dto.getschedule_date(), dto.getSchedule_cost(), dto.getFile_name(), dto.getFile_size());*/

            return ticket_num;
        }

        //게시물 삭제
        @Transactional
        public void delete(final Long ticket_num) {

            scheduleRepository.deleteById(ticket_num);
        }
}

