package com.zealous.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zealous.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
