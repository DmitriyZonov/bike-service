package com.javarush.bikeservice.utils;

import com.javarush.bikeservice.entities.Order;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;

public class OrderPagesGetter {
    @NotNull
    public static Page<Order> getOrderPages(int pageSize, int currentPage, int startItem, List<Order> ordersFromDB) {
        List<Order> ordersInProgress;

        if (ordersFromDB.size() < startItem) {
            ordersInProgress = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, ordersFromDB.size());
            ordersInProgress = ordersFromDB.subList(startItem, toIndex);
        }
        Page<Order> orderPage = new PageImpl<Order>(ordersInProgress, PageRequest.of(currentPage, pageSize), ordersFromDB.size());
        return orderPage;
    }
}
