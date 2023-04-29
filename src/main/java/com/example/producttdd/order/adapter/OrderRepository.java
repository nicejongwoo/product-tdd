package com.example.producttdd.order.adapter;

import com.example.producttdd.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

interface OrderRepository extends JpaRepository<Order, Long> {

}
