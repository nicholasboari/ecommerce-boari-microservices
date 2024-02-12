package com.ecommerceboari.paymentservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "tb_payment")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long paymentId;

    private Long orderId;
    private Date orderDate;
    private Long totalAmount;
}
