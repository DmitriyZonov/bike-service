package com.javarush.bikeservice.entities.bike_service_entities;

import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(schema = "bike_service", name = "bike_parts")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    private Work work;
    @Column(name = "available_in_stock")
    private Boolean isAvailableInStock;
    private Integer price;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Part part = (Part) o;
        return getId() != null && Objects.equals(getId(), part.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
