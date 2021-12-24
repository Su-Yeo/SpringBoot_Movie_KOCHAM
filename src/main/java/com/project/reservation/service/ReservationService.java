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

        Sort sort = Sort.by(Direction.DESC, "seqno", "mregdate");
        List<Board> list = reservationRepository.findAll(sort);
        return list.stream().map(ReservationDto::new).collect(Collectors.toList());
    }

    //게시물 내용 보기
    @Transactional
    public ReservationDto getView(Long seqno) {

        Schedule entity = reservationRepository.findById(seqno).get();
        return new ReservationDto(entity);
    }

    //게시물 수정
    @Transactional
    public Long update(final Long seqno, final ReservationDto params) {

        Schedule entity = reservationRepository.findById(seqno).orElseThrow(()->new IllegalArgumentException("해당 게시물이 없습니다."));

        entity.update(params.getMtitle(), params.getMcontent(), params.getMwriter());
        return seqno;
    }

    //게시물 삭제
    @Transactional
    public void delete(final Long seqno) {

        reservationRepository.deleteById(seqno);
    }
}
*/
