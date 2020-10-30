package com.geekbrains.geekmarket.services;

import com.geekbrains.geekmarket.entities.OrderItem;
import com.geekbrains.geekmarket.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderItemService {
    private OrderItemRepository orderItemRepository;

    @Autowired
    private void setOrderItemRepository(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
}
