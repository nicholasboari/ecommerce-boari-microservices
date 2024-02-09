package com.ecommerceboari.orderservice.repository;

import com.ecommerceboari.orderservice.entity.Order;
import com.ecommerceboari.orderservice.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
}
