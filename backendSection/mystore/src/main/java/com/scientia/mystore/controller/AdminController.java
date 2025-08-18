package com.scientia.mystore.controller;

import com.scientia.mystore.constants.ApplicationConstant;
import com.scientia.mystore.dto.ContactResponseDto;
import com.scientia.mystore.dto.OrderResponseDto;
import com.scientia.mystore.dto.ResponseDto;
import com.scientia.mystore.entity.Order;
import com.scientia.mystore.service.IContactService;
import com.scientia.mystore.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final IOrderService iOrderService;
    private final IContactService iContactService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDto>> getAllPendingOrders() {
        return ResponseEntity.ok().body(iOrderService.getAllPendingOrders());
    }

    @PatchMapping("/orders/{orderId}/confirm")
    public ResponseEntity<ResponseDto> confirmOrder(@PathVariable Long orderId) {
        iOrderService.updateOrderStatus(orderId, ApplicationConstant.ORDER_STATUS_CONFIRMED);
        return ResponseEntity.ok(
                new ResponseDto("200", "Order #" + orderId + " has been approved.")
        );
    }

    @PatchMapping("/orders/{orderId}/cancel")
    public ResponseEntity<ResponseDto> cancelOrder(@PathVariable Long orderId) {
        iOrderService.updateOrderStatus(orderId, ApplicationConstant.ORDER_STATUS_CANCELLED);
        return ResponseEntity.ok(
                new ResponseDto("200", "Order #" + orderId + " has been cancelled.")
        );
    }

    @GetMapping("/messages")
    public ResponseEntity<List<ContactResponseDto>> getAllOpenMessages() {
        return ResponseEntity.ok(iContactService.getAllOpenMessages());
    }

    @PatchMapping("/messages/{contactId}/close")
    public ResponseEntity<ResponseDto> closeMessage(@PathVariable Long contactId) {
        iContactService.updateMessageStatus(contactId, ApplicationConstant.CLOSED_MESSAGE);
        return ResponseEntity.ok(
                new ResponseDto("200", "Contact #" + contactId + " has been closed.")
        );
    }
}
