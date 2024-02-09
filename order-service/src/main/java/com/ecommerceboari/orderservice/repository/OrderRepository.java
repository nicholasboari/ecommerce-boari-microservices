package com.ecommerceboari.orderservice.repository;

import com.ecommerceboari.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
