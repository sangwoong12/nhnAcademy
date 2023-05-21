package com.nhnacademy.resident.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "household")
@Setter @Getter
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "household_serial_number")
    private Long householdSerialNumber;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "household_resident_serial_number")
    private Resident resident;
    @Column(name = "household_composition_date")
    private LocalDate householdCompositionDate;
    @Column(name = "household_composition_reason_code")
    private String householdCompositionReasonCode;
    @Column(name = "current_house_movement_address")
    private String currentHouseMovementAddress;

}
