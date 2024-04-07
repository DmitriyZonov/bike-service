package com.javarush.bikeservice.controllers;

import com.javarush.bikeservice.entities.Order;
import com.javarush.bikeservice.entities.bike_service_entities.Bike;
import com.javarush.bikeservice.entities.bike_service_entities.Client;
import com.javarush.bikeservice.services.ClientService;
import com.javarush.bikeservice.services.OrderService;
import com.javarush.bikeservice.services.WorkService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class AdminOrdersController {

    private final OrderService orderService;
    private final ClientService clientService;
    private final WorkService workService;

    public AdminOrdersController(OrderService orderService, ClientService clientService, WorkService workService) {
        this.orderService = orderService;
        this.clientService = clientService;
        this.workService = workService;
    }

    @GetMapping("/admin/orders/allorders")
    public String getALlOrders(Model model,
                                     @RequestParam("page") Optional<Integer> page,
                                     @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Order> orderPage = orderService.getAllOrders(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("allOrderPage", orderPage);

        int totalPages = orderPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "admin/orders/allorders";
    }
    @GetMapping("/admin/orders/ordersinprogress")
    public String getALlOrdersInWork(Model model,
                                     @RequestParam("page") Optional<Integer> page,
                                     @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Order> orderPage = orderService.getAllOrdersInProgress(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("ordersInWorkPage", orderPage);

        int totalPages = orderPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "admin/orders/ordersinprogress";
    }
    @GetMapping("/admin/orders/closedorders")
    public String getALlClosedOrders(Model model,
                                     @RequestParam("page") Optional<Integer> page,
                                     @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Order> orderPage = orderService.getAllClosedOrders(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("closedOrdersPage", orderPage);

        int totalPages = orderPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "admin/orders/closedorders";
    }
    @GetMapping("/admin/orders/neworder/{clientId}")
    public String addNewOrder(@PathVariable("clientId") Long clientId,
                              Model model) {
        Order order = new Order();
        Client client = clientService.findById(clientId);
        order.setClient(client);
        List<Bike> clientBikes = new ArrayList<>(client.getBikes());

        model.addAttribute("clientBikesList", clientBikes);
        model.addAttribute("order", order);

        return "/admin/orders/neworder";
    }
    @PostMapping("/admin/orders/neworder/saveOrder")
    public String saveOrder(@ModelAttribute("order") Order order){
        orderService.save(order);
        return "redirect:/admin/orders/ordersinprogress";
    }
    @GetMapping("/go-to-closeOrderForm/{id}")
    public String goToCloseOrderForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        return "/admin/orders/closeorderform";
    }
    @PostMapping("/admin/orders/closeorderform/closeOrder")
    public String updateOrder(@ModelAttribute("order") Order order){
        orderService.closeOrder(order);
        return "redirect:/admin/orders/closedorders";
    }
    @GetMapping("/delete-order/{id}")
    public String deleteOrderById(@PathVariable(value = "id") Long id) {
        orderService.deleteById(id);
        return "redirect:/admin/orders/allorders";
    }
}
