package com.scientia.mystore.repository;

import com.scientia.mystore.entity.Customer;
import com.scientia.mystore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerOrderByCreatedAtDesc(Customer customer);

    List<Order> findByOrderStatus(String orderStatus);

    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.orderStatus=:orderStatus,o.updatedAt=CURRENT_TIMESTAMP,o.updatedBy=:updatedBy WHERE o.orderId=:orderId")
    int updateOrderStatus(@Param("orderId") Long orderId, @Param("orderStatus") String orderStatus,
                          @Param("updatedBy") String updatedBy);
}
