package com.ecommerceboari.paymentservice.consumer;

import com.ecommerceboari.paymentservice.producer.PaymentErrorProducer;
import com.ecommerceboari.paymentservice.producer.PaymentSuccessProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class PaymentRequestConsumer {

    private final PaymentErrorProducer errorProducer;
    private final PaymentSuccessProducer successProducer;

    @RabbitListener(queues = {"payment-request-queue"})
    public void receiveMessage(@Payload Message message) {
        if (new Random().nextBoolean()) {
            successProducer.generateResponse("Payment done " + message);
        } else {
            errorProducer.generateResponse("Payment error " + message);
        }
    }
}
