package com.nhnacademy.resident.domain.birth_death_report_resident;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class RegisterReportResidentDto {
    Long targetResidentSerialNumber;
    LocalDate birthDeathReportDate;
    String reportQualificationsCode;
    String emailAddress;
    String phoneNumber;
}
