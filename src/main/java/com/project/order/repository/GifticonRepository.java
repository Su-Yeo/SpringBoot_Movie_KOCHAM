package com.project.order.repository;

import com.project.order.dto.GifticonDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GifticonRepository extends JpaRepository<GifticonDTO, Long> {
    List<GifticonDTO> findAllByMemberId(Long member_id);
    Page<GifticonDTO> findByMemberId(Long member_id, Pageable pageable);
}
