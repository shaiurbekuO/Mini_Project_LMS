package main.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "rent_info")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@SequenceGenerator(name = "id_gen", sequenceName = "rentInfo_seq", allocationSize = 1)
public class RentInfo extends BaseEntity{
    @Column(name = "check_in")
    private String checkIn;
    @Column(name = "check_out")
    private String checkOut;
    @ManyToOne(cascade = {REFRESH, REMOVE, PERSIST, DETACH, MERGE}, fetch = FetchType.EAGER)
    private Owner owners;
    @OneToMany(mappedBy = "rentInfo", cascade ={ PERSIST, REFRESH, DETACH, MERGE}, fetch = FetchType.LAZY)
    private List<Customer>customers;

    public RentInfo(String checkIn, String checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}
