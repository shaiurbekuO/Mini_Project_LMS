package main.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import main.enums.HouseType;

import java.math.BigDecimal;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "houses")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@SequenceGenerator(name = "id_gen", sequenceName = "house_seq", allocationSize = 1)
public class House extends BaseEntity {
    @Column(name = "house_type")
    @Enumerated(EnumType.STRING)
    private HouseType houseType;
    private BigDecimal price;
    private double rating;
    private String description;
    private int room;
    private boolean furniture;
    @ManyToOne(cascade = {REFRESH, DETACH, MERGE, PERSIST}, fetch = FetchType.EAGER)
    private Owner owner;
    @OneToOne(cascade = {DETACH, REFRESH, MERGE, PERSIST})
    private Address address;
    @OneToOne(cascade = {DETACH, REFRESH, MERGE, PERSIST})
    private RentInfo rentInfo;

    public House(HouseType houseType, BigDecimal price, double rating, String description, int room, boolean furniture) {
        this.houseType = houseType;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.room = room;
        this.furniture = furniture;
    }
}
