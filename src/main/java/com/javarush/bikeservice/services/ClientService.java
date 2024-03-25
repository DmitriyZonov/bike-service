package com.javarush.bikeservice.services;

import com.javarush.bikeservice.entities.Order;
import com.javarush.bikeservice.entities.bike_service_entities.Client;
import com.javarush.bikeservice.repositories.jpa_repositories.ClientRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.javarush.bikeservice.constants.ExceptionConstants.CLIENT_NOT_FOUND_EXCEPTION;

@Service
public class ClientService {

    private final ClientRepository clRepo;
    public ClientService(ClientRepository clRepo) {
        this.clRepo = clRepo;
    }
    public List<Client> getAllClients() {
        return clRepo.findAll();
    }
    public Client findByOrder (@NotNull Order order) {
        return order.getClient();
    }
    public Client findById (Integer id) {
        Optional<Client> optional = clRepo.findById(id);
        Client client;

        if (optional.isPresent()) {
            client = optional.get();
        } else {
            throw new NullPointerException(CLIENT_NOT_FOUND_EXCEPTION);
        }
        return client;
    }
    public void saveClient(@NotNull Client client) {
        clRepo.save(client);
    }
    public void deleteById(Integer id) {
        Optional<Client> optional = clRepo.findById(id);

        if (optional.isPresent()) {
            clRepo.deleteById(id);
        } else {
            throw new NullPointerException(CLIENT_NOT_FOUND_EXCEPTION);
        }
    }
}
