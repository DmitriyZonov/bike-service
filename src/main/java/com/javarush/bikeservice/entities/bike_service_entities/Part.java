package com.javarush.bikeservice.entities.bike_service_entities;

import com.javarush.bikeservice.entities.enums.Location;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(schema = "bike_service", name = "bike_parts")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.ORDINAL)
    private Location location;
    private String name;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand")
    private Brand brand;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    private Work work;
    @Column(name = "available_in_stock")
    private Boolean isAvailableInStock;
    private Integer price;

}
