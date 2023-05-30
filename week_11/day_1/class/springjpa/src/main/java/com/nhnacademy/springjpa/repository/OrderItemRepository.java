package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItem.Pk> {
}
