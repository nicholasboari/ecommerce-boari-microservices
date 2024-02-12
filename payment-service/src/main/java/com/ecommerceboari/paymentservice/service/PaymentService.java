package com.ecommerceboari.paymentservice.service;

import com.ecommerceboari.paymentservice.dto.PaymentDTO;
import com.ecommerceboari.paymentservice.producer.PaymentRequestProducer;
import com.ecommerceboari.paymentservice.repository.PaymentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentRequestProducer producer;

    public String requestPayment(PaymentDTO paymentDTO) {
        try {
            producer.integrate(paymentDTO);
        } catch (JsonProcessingException e) {
            return "An error occurred... " + e.getMessage();
        }
        return "Payment waiting confirmation...";
    }

    public void paymentError(String payload) {
        System.err.println("==== Error =====" + payload);
    }

    public void paymentSuccess(String payload) {
        System.out.println("==== Success =====" + payload);
    }
}
