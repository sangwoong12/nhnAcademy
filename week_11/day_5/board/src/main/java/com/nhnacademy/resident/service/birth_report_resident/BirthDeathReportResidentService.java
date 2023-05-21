package com.nhnacademy.resident.service.birth_report_resident;

import com.nhnacademy.resident.domain.birth_death_report_resident.*;
import com.nhnacademy.resident.entity.BirthDeathReportResident;

public interface BirthDeathReportResidentService {
    ReportResidentDto addReportResident(Long serialNumber, RegisterReportResidentDto dto, BirthDeathReportResident.BirthDeathType type);

    ReportResidentDto update(Long serialNumber, Long targetSerialNumber, UpdateReportResidentDto dto, BirthDeathReportResident.BirthDeathType type);

    void delete(Long serialNumber, Long targetSerialNumber, BirthDeathReportResident.BirthDeathType type);

    BirthReportDto findBirthReport(Long serialNumber);

    DeathReportDto findDeathReport(Long serialNumber);

}
