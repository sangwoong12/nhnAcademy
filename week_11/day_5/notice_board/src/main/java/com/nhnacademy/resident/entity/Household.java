package com.nhnacademy.resident.entity;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "household")
public class Household {
    @Id
    private int householdSerialNumber;
    @ManyToOne
    @JoinColumn(name = "household_resident_serial_number")
    private Resident resident;
    private LocalDate householdCompositionDate;
    private String householdCompositionReasonCode;
    private String currentHouseMovementAddress;
}
