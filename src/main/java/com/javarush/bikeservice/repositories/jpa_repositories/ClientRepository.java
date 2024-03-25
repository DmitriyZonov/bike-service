package com.javarush.bikeservice.repositories.jpa_repositories;

import com.javarush.bikeservice.entities.bike_service_entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
