package com.javarush.bikeservice.controllers;

import com.javarush.bikeservice.entities.Order;
import com.javarush.bikeservice.services.OrderService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/get-all-orders")
    public String viewAllOrders(@NotNull Model model, @RequestParam String parameter) {
        switch (parameter) {
            case "1":
                model.addAttribute("allOrdersList", orderService.getAllOrders());
                break;
            case "2":
                model.addAttribute("allOrdersInProgressList", orderService.getAllOrdersInProgress());
                break;
            case "3":
                model.addAttribute("allClosedOrdersList", orderService.getAllClosedOrders());
                break;
        }
        return "orders_page";
    }
    @PostMapping("/save-order")
    public String saveNewOrder(@ModelAttribute("order") Order o, @RequestParam String parameter) {
        orderService.addNewOrder(o);
        return "redirect:/orders_page";
    }
    @GetMapping("/order-update-form/{order_id}")
    public String updateOrder(@PathVariable(value = "order_id") Integer id, @NotNull Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "updateOrder";
    }
    @PostMapping("/delete-order/{order_id}")
    public String deleteOrderById(@PathVariable(value = "order_id") Integer id,
                                  @RequestParam String parameter,
                                  RedirectAttributes redirectAttributes) {
        orderService.deleteById(id);
        redirectAttributes.addAttribute("param", parameter);
        return "redirect: /orders_page?{param}";
    }
}
