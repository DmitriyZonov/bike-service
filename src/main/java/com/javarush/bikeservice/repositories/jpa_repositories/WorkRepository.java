package com.javarush.bikeservice.repositories.jpa_repositories;

import com.javarush.bikeservice.entities.bike_service_entities.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work, Integer> {
}
