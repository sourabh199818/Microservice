package com.mircoservice.sourabh.order.service.repository;

import com.mircoservice.sourabh.order.service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long > {
}
