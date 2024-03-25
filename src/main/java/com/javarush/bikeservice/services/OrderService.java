package com.javarush.bikeservice.services;

import com.javarush.bikeservice.entities.Order;
import com.javarush.bikeservice.repositories.jpa_repositories.OrderRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.javarush.bikeservice.constants.ExceptionConstants.*;

@Service
public class OrderService {
    private final OrderRepository orderRepo;

    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }
    public List<Order> getAllOrdersInProgress() {
        List<Order> allOrders = orderRepo.findAll();
        List<Order> ordersInProgress = new ArrayList<>();
        for(Order order : allOrders) {
            if (order.getInWork()) {
                ordersInProgress.add(order);
            }
        }
        return ordersInProgress;
    }
    public Order getById(Integer id) {
        Optional<Order> optional = orderRepo.findById(id);
        Order order;

        if(optional.isPresent()) {
            order = optional.get();
        } else {
            throw new NullPointerException(ORDER_NOT_FOUND_EXCEPTION);
        }
        return order;
    }
    public void save(@NotNull Order order) {
        orderRepo.save(order);
    }
    public void deleteById(Integer id) {
        Optional<Order> optional = orderRepo.findById(id);

        if(optional.isPresent()) {
            orderRepo.deleteById(id);
        } else {
            throw new NullPointerException(ORDER_NOT_FOUND_EXCEPTION);
        }
    }
}
