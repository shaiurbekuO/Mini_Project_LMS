package main.entity;

import jakarta.persistence.*;
import lombok.*;
import main.enums.Gender;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "owners")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@SequenceGenerator(name = "id_gen", sequenceName = "owner_seq", allocationSize = 1)
public class Owner extends BaseEntity{
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(name = "birht_of_date")
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @ManyToMany(mappedBy = "owners", cascade = {MERGE, REFRESH,DETACH})
    private List<Agency> agencies;
    @ToString.Exclude
    @OneToMany(mappedBy = "owners", cascade = {ALL})
    private List<RentInfo> rentInfos;
    @OneToMany(mappedBy = "owner", cascade = {MERGE, REFRESH, DETACH, PERSIST},fetch = FetchType.LAZY)
    private List<House> houses;

    public Owner(String firstName, String lastName, String email, LocalDate birthDate, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
    }
}
