package com.nhnacademy.resident.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@NamedEntityGraphs({
        @NamedEntityGraph(name = "ResidentWithBDReport", attributeNodes = {
                @NamedAttributeNode("birthDeathReportResidents")
        }),
        @NamedEntityGraph(name = "ResidentWithFamilyRelationships", attributeNodes = {
                @NamedAttributeNode("familyRelationships")
        })
})

@Table(name = "resident")
@Entity
@Getter @Setter
public class Resident {
    @Id
    @Column(name = "resident_serial_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long residentSerialNumber;

    private String name;

    @Column(name = "resident_registration_number")
    private String residentRegistrationNumber;

    @Column(name = "gender_code")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "birth_place_code")
    private String birthPlaceCode;

    @Column(name = "registration_base_address")
    private String registrationBaseAddress;

    @Column(name = "death_date")
    private LocalDateTime deathDate;

    @Column(name = "death_place_code")
    private String deathPlaceCode;

    @Column(name = "death_place_address")
    private String deathPlaceAddress;

    @OneToMany(mappedBy = "baseResident", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    List<FamilyRelationship> familyRelationships;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
    List<Household> households;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
    List<HouseholdCompositionResident> householdCompositionResidents;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
    List<BirthDeathReportResident> birthDeathReportResidents;

    public enum Gender{
        F("여"),
        M("남");

        private String code;

        Gender(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}
