package com.javarush.bikeservice.repositories.jpa_repositories;

import com.javarush.bikeservice.entities.bike_service_entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}
