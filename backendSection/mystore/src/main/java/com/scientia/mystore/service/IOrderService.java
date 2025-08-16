package com.scientia.mystore.service;

import com.scientia.mystore.dto.OrderRequestDto;

public interface IOrderService {

    void createOrder(OrderRequestDto requestDto);
}
