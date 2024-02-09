package com.ecommerceboari.orderservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequestDTO {

    private String userId;
    private List<String> products;
}
