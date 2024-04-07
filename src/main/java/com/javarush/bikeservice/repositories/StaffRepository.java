package com.javarush.bikeservice.repositories;

import com.javarush.bikeservice.entities.bike_service_entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}
