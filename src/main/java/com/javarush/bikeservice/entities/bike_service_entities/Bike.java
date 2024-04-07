package com.javarush.bikeservice.entities.bike_service_entities;

import com.javarush.bikeservice.entities.Order;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(schema = "bike_service", name = "bikes")
public class Bike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bike_id")
    private Long id;
    private String brand;
    private String model;
    @Column(name = "model_year")
    private int yearOfModel;
    @ManyToMany(mappedBy = "bikes")
    private Set<Client> owners;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "bike_id")
    private Set<Order> orders;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Bike bike = (Bike) o;
        return getId() != null && Objects.equals(getId(), bike.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s %s %d", brand, model, yearOfModel);
    }
}
