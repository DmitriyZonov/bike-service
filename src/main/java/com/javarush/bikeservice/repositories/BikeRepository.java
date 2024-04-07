package com.javarush.bikeservice.repositories;

import com.javarush.bikeservice.entities.bike_service_entities.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepository extends JpaRepository<Bike, Long> {
}
