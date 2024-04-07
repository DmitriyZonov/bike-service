package com.javarush.bikeservice.controllers;

import com.javarush.bikeservice.entities.bike_service_entities.Bike;
import com.javarush.bikeservice.entities.bike_service_entities.Client;
import com.javarush.bikeservice.services.ClientService;
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
public class AdminClientsController {

    private final ClientService clientService;

    public AdminClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/admin/clients/allclients")
    public String getAllClients(Model model,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Client> clientPage = clientService.getAllClients(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("allClientPage", clientPage);

        int totalPages = clientPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "admin/clients/allclients";
    }
    @GetMapping("/admin/clients/clientswithactiveorders")
    public String getClientsWithActiveOrders(Model model,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Client> clientPage = clientService.getAllClientsWithOpenOrders(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("clientsInWorkPage", clientPage);

        int totalPages = clientPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "/admin/clients/clientswithactiveorders";
    }
    @GetMapping("/admin/clients/registeredclients")
    public String getRegisteredClients(Model model,
                                             @RequestParam("page") Optional<Integer> page,
                                             @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Client> clientPage = clientService.getAllRegisteredClients(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("registeredClientsPage", clientPage);

        int totalPages = clientPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "/admin/clients/registeredclients";
    }

    @GetMapping("/admin/clients/newclient")
    public String addNewClient(Client client, Model model) {
        return "/admin/clients/newclient";
    }
    @PostMapping("/admin/clients/saveclient")
    public String saveOrder(@ModelAttribute("client") Client client){
        clientService.save(client);
        return "redirect:/admin/clients/allclients";
    }
    @GetMapping("/admin/bikes/clientbikes/{id}")
    public String viewAllBikesOfClient(@PathVariable("id") Long id, Model model) {
        List<Bike> clientBikes = clientService.getAllBikes(id);
        if (!clientBikes.isEmpty()) {
            model.addAttribute("bikeList", clientBikes);
            return "/admin/bikes/clientbikes";
        }
        return "/admin/clients/allclients";
    }
}
