package com.javarush.bikeservice.services;

import com.javarush.bikeservice.entities.Order;
import com.javarush.bikeservice.entities.bike_service_entities.Bike;
import com.javarush.bikeservice.entities.bike_service_entities.Client;
import com.javarush.bikeservice.repositories.ClientRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.javarush.bikeservice.constants.ExceptionConstants.CLIENT_NOT_FOUND_EXCEPTION;
import static com.javarush.bikeservice.utils.ClientPagesGetter.getClientPages;
import static com.javarush.bikeservice.utils.OrderPagesGetter.getOrderPages;

@Service
public class ClientService {

    private final ClientRepository clRepo;
    public ClientService(ClientRepository clRepo) {
        this.clRepo = clRepo;
    }
    public Page<Client> getAllClients(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Client> clientsFromDB = clRepo.findAll();
        return getClientPages(pageSize, currentPage, startItem, clientsFromDB);
    }
    public Page<Client> getAllClientsWithOpenOrders(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Client> clientsFromDB = new ArrayList<>();

        for(Client client : clRepo.findAll()) {
            if (!client.getOrders().isEmpty()) {
                for(Order order : client.getOrders()) {
                    if (order.getInWork()) {
                        clientsFromDB.add(client);
                    }
                }
            }
        }
        return getClientPages(pageSize, currentPage, startItem, clientsFromDB);
    }
    public Page<Client> getAllRegisteredClients(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Client> clientsFromDB = clRepo.getClientsByRegisteredIsTrue();
        return getClientPages(pageSize, currentPage, startItem, clientsFromDB);
    }
    public Client findByOrder (@NotNull Order order) {
        return order.getClient();
    }
    public Client findById (Long id) {
        Optional<Client> optional = clRepo.findById(id);
        Client client;

        if (optional.isPresent()) {
            client = optional.get();
        } else {
            throw new NullPointerException(CLIENT_NOT_FOUND_EXCEPTION);
        }
        return client;
    }
    public List<Bike> getAllBikes(@NotNull Long id) {
        Optional<Client> clientFromDB = clRepo.findById(id);
        List<Bike> bikes;
        if (clientFromDB.isPresent()){
            bikes = new ArrayList<>(clientFromDB.get().getBikes());
            if (bikes.isEmpty()) {
                throw new NullPointerException("Великов нет");
            }
        } else {
            throw new NullPointerException(CLIENT_NOT_FOUND_EXCEPTION);
        }
        return bikes;
    }
    public void save(@NotNull Client client) {
        clRepo.save(client);
    }
    public void deleteById(Long id) {
        Optional<Client> optional = clRepo.findById(id);

        if (optional.isPresent()) {
            clRepo.deleteById(id);
        } else {
            throw new NullPointerException(CLIENT_NOT_FOUND_EXCEPTION);
        }
    }
}
