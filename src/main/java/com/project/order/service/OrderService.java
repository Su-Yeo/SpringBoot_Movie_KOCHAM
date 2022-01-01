package com.project.order.service;

import com.project.order.dto.OrderDTO;
import com.project.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderDTO saveOrder(OrderDTO order){
        order.setOrder_date(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public Page<OrderDTO> findOrder(Long member_id, Pageable pageable){
        Page<OrderDTO> orders = orderRepository.findAllByMemberId(member_id, pageable);
        return orders;
    }

}
