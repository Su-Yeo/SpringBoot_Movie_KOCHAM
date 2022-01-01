package com.project.order.repository;

import com.project.order.dto.GifticonDTO;
import com.project.store.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GifticonRepository extends JpaRepository<GifticonDTO, Long> {
    List<GifticonDTO> findAllByMemberId(Long member_id);
}
