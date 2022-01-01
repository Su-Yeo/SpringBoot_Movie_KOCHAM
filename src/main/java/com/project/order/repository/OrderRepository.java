package com.project.order.repository;

import com.project.order.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderDTO, Long> {
   // List<OrderDTO> findAllByMemberId(Long member_id);
    Page<OrderDTO> findAllByMemberId(Long member_id, Pageable pageable);
}
