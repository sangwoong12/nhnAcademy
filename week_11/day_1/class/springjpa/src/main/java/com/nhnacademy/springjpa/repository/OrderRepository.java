package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
}
