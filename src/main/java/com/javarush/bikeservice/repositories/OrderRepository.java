package com.javarush.bikeservice.repositories;

import com.javarush.bikeservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByInWorkIsTrue();
    List<Order> findOrdersByInWorkIsFalse();
}
