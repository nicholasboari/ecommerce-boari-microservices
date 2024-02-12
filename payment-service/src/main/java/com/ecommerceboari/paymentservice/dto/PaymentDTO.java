package com.ecommerceboari.paymentservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PaymentDTO {

    private String orderId;
    private Date orderDate;
    private Long totalAmount;
}
