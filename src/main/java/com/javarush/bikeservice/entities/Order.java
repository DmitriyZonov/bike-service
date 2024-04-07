package com.javarush.bikeservice.entities;

import com.javarush.bikeservice.entities.bike_service_entities.Bike;
import com.javarush.bikeservice.entities.bike_service_entities.Client;
import com.javarush.bikeservice.entities.bike_service_entities.Work;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(schema = "bike_service", name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
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
    @ManyToMany(mappedBy = "orders")
    private Set<Work> works;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Order order = (Order) o;
        return getId() != null && Objects.equals(getId(), order.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
