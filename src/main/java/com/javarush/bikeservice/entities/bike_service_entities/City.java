package com.javarush.bikeservice.entities.bike_service_entities;

import javax.persistence.*;

import com.javarush.bikeservice.entities.Order;
import lombok.Data;

import java.util.Set;

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
    @OneToMany
    @JoinColumn(name = "city_id")
    private Set<Client> clients;
}
