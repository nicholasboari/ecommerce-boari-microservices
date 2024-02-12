package com.ecommerceboari.paymentservice.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentSuccessProducer {

    private final AmqpTemplate amqpTemplate;

    public void generateResponse(String message) {
        amqpTemplate.convertAndSend(
                "payment-response-success-exchange",
                "payment-response-success-rout-key",
                message);
    }
}
