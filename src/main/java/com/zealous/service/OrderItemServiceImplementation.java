package com.zealous.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zealous.model.OrderItem;
import com.zealous.repository.OrderItemRepository;

@Service
public class OrderItemServiceImplementation implements OrderItemService {
    @Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		
		return orderItemRepository.save(orderItem);
	}

}
