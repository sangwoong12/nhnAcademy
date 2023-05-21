package com.nhnacademy.resident.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "family_relationship")
@Entity
@Getter @Setter
public class FamilyRelationship {

    @EmbeddedId
    private Pk pk;

    @ManyToOne
    @MapsId("familyResidentSerialNumber")
    @JoinColumn(name = "family_resident_serial_number", referencedColumnName = "resident_serial_number", insertable = false, updatable = false)
    private Resident familyResident;

    @ManyToOne
    @MapsId("baseResidentSerialNumber")
    @JoinColumn(name = "base_resident_serial_number", referencedColumnName = "resident_serial_number", insertable = false, updatable = false)
    private Resident baseResident;

    @Column(name = "family_relationship_code")
    private String familyRelationshipCode;

    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Pk implements Serializable {
        @Column(name = "family_resident_serial_number")
        private Long familyResidentSerialNumber;
        @Column(name = "base_resident_serial_number")
        private Long baseResidentSerialNumber;
    }
}
