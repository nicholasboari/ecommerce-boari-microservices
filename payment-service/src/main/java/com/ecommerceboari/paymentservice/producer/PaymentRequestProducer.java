package com.ecommerceboari.paymentservice.producer;

import com.ecommerceboari.paymentservice.dto.PaymentDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentRequestProducer {

    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    public void integrate(PaymentDTO paymentDTO) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                "payment-request-exchange",
                "payment-request-rout-key",
                objectMapper.writeValueAsString(paymentDTO));
    }
}
