package com.nhnacademy.resident.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "family_relationship")
@Entity
@Getter
public class FamilyRelationShip {

    @EmbeddedId
    private Pk pk;

    @ManyToOne
    @MapsId("familyResidentSerialNumber")
    @JoinColumn(name = "family_resident_serial_number")
    private Resident familyResident;

    @ManyToOne
    @MapsId("baseFResidentSerialNumber")
    @JoinColumn(name = "base_resident_serial_number")
    private Resident baseResident;
    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Pk implements Serializable {
        private int familyResidentSerialNumber;
        private int baseFResidentSerialNumber;
    }
}
