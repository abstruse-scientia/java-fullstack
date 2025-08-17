package com.scientia.mystore.service;

import com.scientia.mystore.dto.OrderRequestDto;
import com.scientia.mystore.dto.OrderResponseDto;
import com.scientia.mystore.entity.Order;

import java.util.List;

public interface IOrderService {

    void createOrder(OrderRequestDto requestDto);

    List<OrderResponseDto> getCustomerOrders();

    List<OrderResponseDto> getAllPendingOrders();

    Order updateOrderStatus(Long orderId, String orderStatus);
}
