package com.javarush.bikeservice.entities.bike_service_entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(schema = "bike_service", name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String region;
    private String district;
}
