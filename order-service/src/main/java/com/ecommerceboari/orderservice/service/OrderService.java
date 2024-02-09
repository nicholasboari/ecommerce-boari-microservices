package com.ecommerceboari.orderservice.service;

import com.ecommerceboari.orderservice.dto.CreateOrderRequestDTO;
import com.ecommerceboari.orderservice.dto.CreateOrderResponseDTO;
import com.ecommerceboari.orderservice.dto.ProductResponseDTO;
import com.ecommerceboari.orderservice.entity.Order;
import com.ecommerceboari.orderservice.entity.ProductOrder;
import com.ecommerceboari.orderservice.exception.BadRequestException;
import com.ecommerceboari.orderservice.repository.OrderRepository;
import com.ecommerceboari.orderservice.repository.ProductOrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final ProductOrderRepository productOrderRepository;
    private final RestTemplate restTemplate;

    @Transactional
    public CreateOrderResponseDTO save(CreateOrderRequestDTO createOrderRequestDTO) {
        Long totalAmount = 0L;
        List<ProductOrder> productOrders = new ArrayList<>();

        Order order = Order.builder()
                .userId(createOrderRequestDTO.getUserId())
                .orderDate(new Date())
                .build();
        Order orderSaved = orderRepository.save(order);

        for (String productId : createOrderRequestDTO.getProducts()) {
            String url = "http://localhost:8082/api/v1/products?id=" + productId;
            ResponseEntity<ProductResponseDTO> response = restTemplate.getForEntity(url, ProductResponseDTO.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                ProductResponseDTO productResponseDTO = response.getBody();
                totalAmount += productResponseDTO.getPrice();

                ProductOrder productOrder = ProductOrder.builder()
                        .orderId(orderSaved.getOrderId())
                        .productId(productResponseDTO.getId())
                        .subtotal(productResponseDTO.getPrice())
                        .build();
                productOrders.add(productOrder);
            } else {
                throw new BadRequestException("Product ID not found");
            }
        }

        productOrderRepository.saveAll(productOrders);

        orderSaved.setTotalAmount(totalAmount);
        orderRepository.save(orderSaved);

        LOGGER.info("Inserting {} into the database", orderSaved);
        return modelMapper.map(orderSaved, CreateOrderResponseDTO.class);
    }
}
