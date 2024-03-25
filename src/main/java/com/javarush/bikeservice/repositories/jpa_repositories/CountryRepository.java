package com.javarush.bikeservice.repositories.jpa_repositories;

import com.javarush.bikeservice.entities.bike_service_entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
