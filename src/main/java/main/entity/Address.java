package main.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.*;


@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@SequenceGenerator(name = "id_gen", sequenceName = "address_seq", allocationSize = 1)
public class Address extends BaseEntity{
    private String city;
    private String region;
    private String street;
    @OneToOne(mappedBy = "address",cascade = {ALL})
    private Agency agency;

    public Address(String city, String region, String street) {
        this.city = city;
        this.region = region;
        this.street = street;
    }
}
