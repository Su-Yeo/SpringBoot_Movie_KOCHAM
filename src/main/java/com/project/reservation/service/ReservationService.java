/*
package com.project.reservation.service;

import com.project.reservation.dto.ReservationDto;
import com.project.reservation.entity.Schedule;
import com.project.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;


    //게시글 등록
    @Transactional
    public Long save(final ReservationDto params) {

        Schedule entity = reservationRepository.save(params.toEntity());
        return entity.schedule_num();
    }

    //게시물 목록 보기
    public List<ReservationDto> findAll(){

        Sort sort = Sort.by(Direction.DESC, "schedule_num", "mregdate");
        List<Board> list = reservationRepository.findAll(sort);
        return list.stream().map(ReservationDto::new).collect(Collectors.toList());
    }

    //게시물 내용 보기
    @Transactional
    public ReservationDto getView(Long schedule_num) {

        Schedule entity = reservationRepository.findById(schedule_num).get();
        return new ReservationDto(entity);
    }

    //게시물 수정
    @Transactional
    public Long update(final Long schedule_num, final ReservationDto params) {

        Schedule entity = reservationRepository.findById(schedule_num).orElseThrow(()->new IllegalArgumentException("해당 게시물이 없습니다."));

        entity.update(params.getmovie_num(), params.getMcontent(), params.getschedule_start());
        return schedule_num;
    }

    //게시물 삭제
    @Transactional
    public void delete(final Long schedule_num) {

        reservationRepository.deleteById(schedule_num);
    }
}
*/
