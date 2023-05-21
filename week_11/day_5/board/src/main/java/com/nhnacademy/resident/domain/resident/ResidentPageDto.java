package com.nhnacademy.resident.domain.resident;

import com.nhnacademy.resident.entity.BirthDeathReportResident;

import java.util.List;

public interface ResidentPageDto {
    String getName();
    Long getResidentSerialNumber();
    List<BirthDeathReportResidentDto> getBirthDeathReportResidents();
    interface BirthDeathReportResidentDto {
        BirthDeathReportResident.Pk getPk();
    }
}
