package com.javarush.bikeservice.services;

import com.javarush.bikeservice.entities.Order;
import com.javarush.bikeservice.repositories.OrderRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.javarush.bikeservice.constants.ExceptionConstants.*;
import static java.util.Objects.isNull;

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
        List<Order> ordersInProgress = new ArrayList<>();
        for(Order order : orderRepo.findAll()) {
            if (order.getInWork()) {
                ordersInProgress.add(order);
            }
        }
        return ordersInProgress;
    }
    public List<Order> findAllClosedOrders() {
        List<Order> closedOrders = new ArrayList<>();
        for(Order order : orderRepo.findAll()) {
            if (!order.getInWork()) {
                closedOrders.add(order);
            }
        }
        return closedOrders;
    }
    public Order getOrderById(Integer id) {
        Order order = orderRepo.findById(id);
        if(order != null) {
            return order;
        } else {
            throw new NullPointerException(ORDER_NOT_FOUND_EXCEPTION);
        }
    }
    public void addNewOrder(@NotNull Order order) {
        if(!isNull(order.getClient()) && !isNull(order.getBike())) {
            orderRepo.save(order);
        } else {
            throw new IllegalStateException(NO_CLIENT_OR_BIKE);
        }
    }
    public void updateOrder(@NotNull Order order) {
        orderRepo.update(order);
    }
    public void closeOrder(@NotNull Order order, @NotNull Integer cost) {
            order.setCost(cost);
            orderRepo.close_order(order);
    }
    public void deleteById(@NotNull Integer id) {
        Order order = orderRepo.findById(id);
        if(order != null) {
            orderRepo.deleteById(id);
        } else {
            throw new NullPointerException(ORDER_NOT_FOUND_EXCEPTION);
        }
    }
}