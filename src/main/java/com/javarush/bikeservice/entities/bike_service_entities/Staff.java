package com.javarush.bikeservice.entities.bike_service_entities;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(schema = "bike_service", name = "employee")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String passport;
    private String name;
    private String occupation;
    @Column(name = "phone_number")
    private String phoneNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city")
    private City city;
    private String address;
    @Column(name = "is_hired")
    private Boolean isHired;
}
