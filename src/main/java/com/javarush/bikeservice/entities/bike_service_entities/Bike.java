package com.javarush.bikeservice.entities.bike_service_entities;

import com.javarush.bikeservice.entities.Order;
import com.javarush.bikeservice.entities.enums.BikeType;
import javax.persistence.*;
import lombok.Data;

import java.time.Year;
import java.util.Set;

@Entity
@Data
@Table(schema = "bike_service", name = "bikes")
public class Bike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bike_id")
    private Integer id;
    @OneToOne
    @JoinColumn(name = "brand")
    private Brand brand;
    private String model;
    @Column(name = "model_year")
    private Year yearOfModel;
    @Enumerated(EnumType.ORDINAL)
    private BikeType type;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "client_bike",
            joinColumns = @JoinColumn(name = "bike_id", referencedColumnName = "bike_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    )
    private Set<Client> owners;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "bike_id")
    private Set<Order> orders;
}
