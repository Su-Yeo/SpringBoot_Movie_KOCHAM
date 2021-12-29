package com.project.order.service;

import com.project.order.dto.OrderDTO;
import com.project.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderDTO saveOrder(OrderDTO order){
        order.setOrder_date(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public List<OrderDTO> findOrder(){
        List<OrderDTO> orders = orderRepository.findAll();
        return orders;
    }

}
