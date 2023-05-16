package com.nhnacademy.resident.entity;

import javax.persistence.*;
import java.time.LocalDate;
//TODO 수정.
@Entity
@Table(name = "household_movement_address")
public class HouseholdMovementAddress {
    @Id
    private LocalDate houseMovementRepostDate;
    @ManyToOne
    @JoinColumn(name = "household_serial_number")
    private Household household;

    private String houseMovementAddress;
    private boolean lastAddressYn;
}
