package com.scientia.mystore.service.impl;

import com.scientia.mystore.dto.PaymentIntentRequestDto;
import com.scientia.mystore.dto.PaymentIntentResponseDto;
import com.scientia.mystore.service.IPaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements IPaymentService {
    @Override
    public PaymentIntentResponseDto createPaymentIntent(PaymentIntentRequestDto requestDto) {
        try {
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(requestDto.amount())
                    .setCurrency(requestDto.currency().toLowerCase())
                    .addPaymentMethodType("card").build();
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            return new PaymentIntentResponseDto(paymentIntent.getClientSecret());
        } catch (StripeException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create payment intent" + e.getMessage(), e);
        }

    }
}
