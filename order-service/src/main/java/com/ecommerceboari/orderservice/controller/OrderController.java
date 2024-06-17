package com.ecommerceboari.orderservice.controller;

import com.ecommerceboari.orderservice.dto.CreateOrderRequestDTO;
import com.ecommerceboari.orderservice.dto.CreateOrderResponseDTO;
import com.ecommerceboari.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<CreateOrderResponseDTO> save(@RequestBody CreateOrderRequestDTO createOrderRequestDTO) {
        CreateOrderResponseDTO createOrderResponseDTO = orderService.save(createOrderRequestDTO);
        LOGGER.info("Received request to create a new product");
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(createOrderResponseDTO);
    }
}
