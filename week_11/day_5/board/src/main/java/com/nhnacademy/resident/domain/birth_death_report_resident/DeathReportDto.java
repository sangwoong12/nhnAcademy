package com.nhnacademy.resident.domain.birth_death_report_resident;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DeathReportDto {
    TargetResident getResident();

    ReportResident getReportResident();

    String getDeathReportQualificationsCode();

    LocalDate getBirthDeathReportDate();

    String getEmailAddress();

    String getPhoneNumber();

    interface TargetResident {
        String getName();

        LocalDateTime getDeathDate();

        String getDeathPlaceCode();

        String getDeathPlaceAddress();

        String getResidentRegistrationNumber();
    }

    interface ReportResident {
        String getName();

        String getResidentRegistrationNumber();
    }
}
