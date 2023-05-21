package com.nhnacademy.resident.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "household_movement_address")
@Setter @Getter
public class HouseholdMovementAddress {
    @EmbeddedId
    private Pk pk;
    @ManyToOne
    @MapsId("householdSerialNumber")
    @JoinColumn(name = "household_serial_number")
    private Household household;
    @Column(name = "house_movement_address")
    private String houseMovementAddress;
    @Column(name = "last_address_yn")
    @Enumerated(EnumType.STRING)
    private LastAddressYn lastAddressYn;

    public enum LastAddressYn {
        Y("Y"),N("N");
        private String code;

        LastAddressYn(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Pk implements Serializable {
        @Column(name = "household_serial_number")
        private Long householdSerialNumber;
        @Column(name = "house_movement_report_date")
        private LocalDate houseMovementReportDate;
    }
}
