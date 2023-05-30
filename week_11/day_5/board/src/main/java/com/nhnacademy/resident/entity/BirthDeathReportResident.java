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

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "report_resident_serial_number")
    private Resident reportResident;
    @Column(name = "birth_death_report_date")
    private LocalDate birthDeathReportDate;
    @Column(name = "birth_report_qualifications_code")
    private String birthReportQualificationsCode;
    @Column(name = "death_report_qualifications_code")
    private String deathReportQualificationsCode;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "phone_number")
    private String phoneNumber;


    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Pk implements Serializable {
        @Column(name = "birth_death_type_code")
        @Enumerated(EnumType.STRING)
        private BirthDeathType birthDeathTypeCode;
        @Column(name = "residnet_serial_number")
        private Long residentSerialNumber;
    }
    public enum BirthDeathType {
        BIRTH("출생"),
        DEATH("사망");

        private String code;

        BirthDeathType(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}