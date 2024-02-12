package com.ecommerceboari.paymentservice.consumer;

import com.ecommerceboari.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PaymentResponseErrorConsumer {

    private final PaymentService paymentService;

    @RabbitListener(queues = {"payment-response-error-queue"})
    public void receive(@Payload Message message){
        System.out.println("Message " + message + " " + LocalDateTime.now());
        String payload = String.valueOf(message.getPayload());
        paymentService.paymentError(payload);
    }
}
