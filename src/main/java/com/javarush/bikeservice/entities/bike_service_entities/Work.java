package com.javarush.bikeservice.entities.bike_service_entities;


import com.javarush.bikeservice.entities.enums.Location;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(schema = "bike_service", name = "works")
public class Work {
    @Id
    private String name;
    private Integer price;
    @Enumerated(EnumType.ORDINAL)
    private Location location;
}
