package com.javarush.bikeservice.entities;

import com.javarush.bikeservice.entities.bike_service_entities.Bike;
import com.javarush.bikeservice.entities.bike_service_entities.Client;
import com.javarush.bikeservice.entities.bike_service_entities.Work;
import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(schema = "bike_service", name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bike_id")
    private Bike bike;
    private Integer cost;
    @Column(name = "acceptance_date")
    private LocalDateTime dateOfAcceptance;
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    private Boolean inWork;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_work",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "work_id", referencedColumnName = "id")
    )
    private Set<Work> works;
    public Order(Client client, Bike bike) {
        this.client = client;
        this.bike = bike;
        this.dateOfAcceptance = LocalDateTime.now();
        this.inWork = true;
    }
    public Order(Client client, Bike bike, Set<Work> works) {
        this.client = client;
        this.bike = bike;
        this.works = works;
        this.dateOfAcceptance = LocalDateTime.now();
        this.inWork = true;
    }
}
