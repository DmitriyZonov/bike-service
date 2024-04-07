package com.javarush.bikeservice.repositories;

import com.javarush.bikeservice.entities.Order;
import com.javarush.bikeservice.entities.bike_service_entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> getClientsByRegisteredIsTrue();
}
