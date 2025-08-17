package com.scientia.mystore.repository;

import com.scientia.mystore.entity.Customer;
import com.scientia.mystore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerOrderByCreatedAtDesc(Customer customer);

    List<Order> findByOrderStatus(String orderStatus);
}
