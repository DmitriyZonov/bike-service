package com.javarush.bikeservice.repositories;

import com.javarush.bikeservice.entities.bike_service_entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
