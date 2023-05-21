package com.nhnacademy.resident.domain.birth_death_report_resident;

import com.nhnacademy.resident.entity.Resident;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface BirthReportDto {
    TargetResident getResident();
    ReportResident getReportResident();
    String getBirthReportQualificationsCode();
    LocalDate getBirthDeathReportDate();
    String getEmailAddress();
    String getPhoneNumber();

    interface TargetResident {
        String getName();
        Resident.Gender getGender();
        LocalDateTime getBirthDate();
        String getBirthPlaceCode();
        String getRegistrationBaseAddress();
    }
    interface ReportResident {
        String getName();
        String getResidentRegistrationNumber();
    }
}
