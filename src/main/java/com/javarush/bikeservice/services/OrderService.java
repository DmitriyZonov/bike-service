package com.javarush.bikeservice.services;

import com.javarush.bikeservice.entities.Order;
import com.javarush.bikeservice.repositories.OrderRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.javarush.bikeservice.constants.ExceptionConstants.*;
import static com.javarush.bikeservice.utils.OrderPagesGetter.getOrderPages;
import static java.util.Objects.isNull;

@Service
public class OrderService {
    private final OrderRepository orderRepo;

    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }
    public Page<Order> getAllOrders(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Order> ordersFromDB = orderRepo.findAll();
        return getOrderPages(pageSize, currentPage, startItem, ordersFromDB);
    }
    public Page<Order> getAllOrdersInProgress(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Order> ordersFromDB = orderRepo.findOrdersByInWorkIsTrue();
        return getOrderPages(pageSize, currentPage, startItem, ordersFromDB);
    }
    public Page<Order> getAllClosedOrders(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Order> ordersFromDB = orderRepo.findOrdersByInWorkIsFalse();
        return getOrderPages(pageSize, currentPage, startItem, ordersFromDB);
    }
    public Order getOrderById(Long id) {
        Optional<Order> optional = orderRepo.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            throw new NullPointerException(ORDER_NOT_FOUND_EXCEPTION);
        }
    }
    public void save (@NotNull Order order) {
        if(isNull(order.getClient())) {
            throw new IllegalStateException(NO_CLIENT);
        } else if (isNull(order.getBike())) {
            throw new IllegalStateException(NO_BIKE);
        }
        order.setDateOfAcceptance(LocalDateTime.now());
        order.setInWork(true);
        orderRepo.save(order);
    }
    public void closeOrder(@NotNull Order order) {
            order.setPaymentDate(LocalDateTime.now());
            order.setInWork(false);
            orderRepo.save(order);
    }
    public void deleteById(@NotNull Long id) {
        Optional<Order> optional = orderRepo.findById(id);
        if(optional.isPresent()) {
            orderRepo.deleteById(id);
        } else {
            throw new NullPointerException(ORDER_NOT_FOUND_EXCEPTION);
        }
    }
}