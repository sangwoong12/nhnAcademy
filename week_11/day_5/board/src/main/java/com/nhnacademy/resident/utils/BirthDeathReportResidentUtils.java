package com.nhnacademy.resident.utils;

import com.nhnacademy.resident.domain.birth_death_report_resident.RegisterReportResidentDto;
import com.nhnacademy.resident.domain.birth_death_report_resident.UpdateReportResidentDto;
import com.nhnacademy.resident.entity.BirthDeathReportResident;

public class BirthDeathReportResidentUtils {
    private BirthDeathReportResidentUtils() {
    }

    public static BirthDeathReportResident createByDto(BirthDeathReportResident.BirthDeathType type, RegisterReportResidentDto dto) {
        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();
        birthDeathReportResident.setPk(createPk(type,dto.getTargetResidentSerialNumber()));
        birthDeathReportResident.setBirthDeathReportDate(dto.getBirthDeathReportDate());
        birthDeathReportResident.setEmailAddress(dto.getEmailAddress());
        birthDeathReportResident.setPhoneNumber(dto.getPhoneNumber());
        if (type.equals(BirthDeathReportResident.BirthDeathType.DEATH)) {
            birthDeathReportResident.setDeathReportQualificationsCode(dto.getReportQualificationsCode());
        } else {
            birthDeathReportResident.setBirthReportQualificationsCode(dto.getReportQualificationsCode());
        }
        return birthDeathReportResident;
    }

    public static BirthDeathReportResident updateByDto(BirthDeathReportResident birthDeathReportResident, UpdateReportResidentDto dto, BirthDeathReportResident.BirthDeathType type) {
        birthDeathReportResident.setBirthDeathReportDate(dto.getBirthDeathReportDate());
        birthDeathReportResident.setEmailAddress(dto.getEmailAddress());
        birthDeathReportResident.setPhoneNumber(dto.getPhoneNumber());
        if (type.equals(BirthDeathReportResident.BirthDeathType.DEATH)) {
            birthDeathReportResident.setDeathReportQualificationsCode(dto.getReportQualificationsCode());
        } else {
            birthDeathReportResident.setBirthReportQualificationsCode(dto.getReportQualificationsCode());
        }
        return birthDeathReportResident;
    }

    public static BirthDeathReportResident.Pk createPk(BirthDeathReportResident.BirthDeathType type, Long serialNumber) {
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
        pk.setBirthDeathTypeCode(type);
        pk.setResidentSerialNumber(serialNumber);
        return pk;
    }
}
