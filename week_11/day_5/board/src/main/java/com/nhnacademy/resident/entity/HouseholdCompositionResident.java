package com.nhnacademy.resident.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "household_composition_resident")
public class HouseholdCompositionResident {
    @EmbeddedId
    private Pk pk;

    @ManyToOne
    @MapsId("householdSerialNumber")
    @JoinColumn(name = "household_serial_number")
    private Household household;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @MapsId("residentSerialNumber")
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @Column(name = "report_date")
    private LocalDate reportDate;
    @Column(name = "household_relationship_code")
    private String householdRelationshipCode;
    @Column(name = "household_composition_change_reason_code")
    private String householdCompositionChangeReasonCode;

    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Pk implements Serializable {
        private Long householdSerialNumber;
        private Long residentSerialNumber;
    }
}
