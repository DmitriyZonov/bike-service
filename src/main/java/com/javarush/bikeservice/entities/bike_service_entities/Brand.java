package com.javarush.bikeservice.entities.bike_service_entities;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(schema = "bike_service", name = "bikes_and_parts_brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_country")
    private Country countryOfHeadOffice;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "production_country")
    private Country countryOfProduction;
}
