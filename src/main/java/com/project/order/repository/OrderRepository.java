package com.project.order.repository;

import com.project.order.dto.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDTO, Long> {

   
}
