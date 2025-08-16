package com.scientia.mystore.service;

import com.scientia.mystore.dto.PaymentIntentRequestDto;
import com.scientia.mystore.dto.PaymentIntentResponseDto;

public interface IPaymentService {
    PaymentIntentResponseDto createPaymentIntent(PaymentIntentRequestDto requestDto);
}
