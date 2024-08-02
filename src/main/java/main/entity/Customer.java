package main.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import main.enums.FamilyStatus;
import main.enums.Gender;

import java.time.LocalDate;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@SequenceGenerator(name = "id_gen", sequenceName = "customer_seq", allocationSize = 1)
public class Customer extends BaseEntity{
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(name = "birht_of_date")
    private LocalDate birthOfDate;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String nationlity;
    private FamilyStatus familyStatus;
    @ManyToOne(cascade = {REFRESH, PERSIST, DETACH, MERGE})
    private RentInfo rentInfo;

    public Customer(String firstName, String lastName, String email, LocalDate birthOfDate, LocalDate birthDate, Gender gender, String nationlity, FamilyStatus familyStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthOfDate = birthOfDate;
        this.birthDate = birthDate;
        this.gender = gender;
        this.nationlity = nationlity;
        this.familyStatus = familyStatus;
    }
}
