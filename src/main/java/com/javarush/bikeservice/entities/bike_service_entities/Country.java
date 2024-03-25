package com.javarush.bikeservice.entities.bike_service_entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(schema = "bike_service", name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
