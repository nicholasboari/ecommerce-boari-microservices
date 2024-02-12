package com.ecommerceboari.paymentservice.controller;

import com.ecommerceboari.paymentservice.dto.PaymentDTO;
import com.ecommerceboari.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public String process(@RequestBody PaymentDTO paymentDTO){
        return paymentService.requestPayment(paymentDTO);
    }
}
