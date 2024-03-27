package com.javarush.bikeservice.entities.bike_service_entities;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(schema = "bike_service", name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Id
    private String passport;
    private String name;
    private String occupation;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city")
    private City city;
    private String address;
    @Column(name = "is_hired")
    private Boolean isHired;
}
