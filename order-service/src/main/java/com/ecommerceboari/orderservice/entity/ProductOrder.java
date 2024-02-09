package com.ecommerceboari.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tb_product_order")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_order_id")
    private Long productOrderId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_id")
    private String productId;

    private Long subtotal;
}