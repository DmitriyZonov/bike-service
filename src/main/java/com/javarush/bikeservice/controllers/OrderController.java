package com.javarush.bikeservice.controllers;

import com.javarush.bikeservice.entities.Order;
import com.javarush.bikeservice.services.OrderService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/getAllOrders")
    public String viewAllOrders(@NotNull Model model) {
        model.addAttribute("allOrdersList", orderService.getAllOrders());
        return "allOrders";
    }
    @PostMapping("/saveOrder")
    public String saveNewOrder(@ModelAttribute("order") Order o) {
        orderService.addNewOrder(o);

        return "redirect:/";
    }
    @GetMapping("/order-update-form/{id}")
    public String updateOrder(@PathVariable(value = "id") Integer id, @NotNull Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "updateOrder";
    }
}
