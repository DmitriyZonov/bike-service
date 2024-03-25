package com.javarush.bikeservice.repositories.jpa_repositories;

import com.javarush.bikeservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
