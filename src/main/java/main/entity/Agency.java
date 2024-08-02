package main.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "agencys")
@Getter
@Setter
@ToString
@NoArgsConstructor
@SequenceGenerator(name = "id_gen", sequenceName = "agency_seq", allocationSize = 1)
public class Agency extends BaseEntity{
    private String name;
    private int phoneNumber;
    @OneToOne(cascade = {REFRESH, REMOVE, PERSIST, DETACH, MERGE}, fetch = FetchType.LAZY)
    private Address address;
    @ManyToMany(cascade = {REFRESH, REMOVE, PERSIST, DETACH, MERGE})
    private List<Owner> owners;
    @OneToMany(cascade = {REFRESH, REMOVE, PERSIST, DETACH, MERGE}, fetch = FetchType.LAZY)
    private List<RentInfo> rentInfos;

    public Agency(String name, int phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }


}
