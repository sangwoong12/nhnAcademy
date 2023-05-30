package com.nhnacademy.resident.domain.birth_death_report_resident;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class UpdateReportResidentDto {
    @NotNull
    LocalDate birthDeathReportDate;
    @NotNull
    String reportQualificationsCode;
    String emailAddress;
    String phoneNumber;
}
