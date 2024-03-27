package com.javarush.bikeservice.entities.bike_service_entities;

import com.javarush.bikeservice.entities.Order;
import javax.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(schema = "bike_service", name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Integer id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;
    private String address;
    @Column(name = "phone_number")
    private String PhoneNumber;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "client_bike",
            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "bike_id", referencedColumnName = "bike_id")
    )
    private Set<Bike> bikes;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Set<Order> orders;
}
