package com.javarush.bikeservice.entities.bike_service_entities;


import com.javarush.bikeservice.entities.Order;
import com.javarush.bikeservice.entities.enums.Location;
import javax.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(schema = "bike_service", name = "works")
public class Work {
    @Id
    private String name;
    private Integer price;
    @Enumerated(EnumType.ORDINAL)
    private Location location;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_work",
            joinColumns = @JoinColumn(name = "work_name", referencedColumnName = "name"),
            inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    )
    private Set<Order> orders;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Set<Part> parts;
}
