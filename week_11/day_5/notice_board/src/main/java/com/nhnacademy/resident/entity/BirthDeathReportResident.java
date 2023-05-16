package com.nhnacademy.resident.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "birth_death_report_resident")
public class BirthDeathReportResident {

    @EmbeddedId
    private Pk pk;

    @ManyToOne
    @MapsId("residentSerialNumber")
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @ManyToOne
    @JoinColumn(name = "report_resident_serial_number")
    private Resident reportResident;
    private LocalDate birthDeathReportDate;
    private String birthReportQualificationsCode;
    private String deathReportQualificationsCode;
    private String emailAddress;
    private String phoneNumber;

    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Pk implements Serializable {
        private BirthDeathType birthDeathTypeCode;
        private Long residentSerialNumber;
    }

}