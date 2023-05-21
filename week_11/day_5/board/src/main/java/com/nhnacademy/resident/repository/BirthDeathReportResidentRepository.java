package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.domain.birth_death_report_resident.BirthReportDto;
import com.nhnacademy.resident.domain.birth_death_report_resident.DeathReportDto;
import com.nhnacademy.resident.domain.birth_death_report_resident.ReportResidentDto;
import com.nhnacademy.resident.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthDeathReportResidentRepository extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.Pk> {
    ReportResidentDto findByPk(BirthDeathReportResident.Pk pk);
    void deleteByPkAndReportResident_ResidentSerialNumber(BirthDeathReportResident.Pk pk, Long serialNumber);
    BirthReportDto findBirthByPk(BirthDeathReportResident.Pk pk);
    DeathReportDto findDeathByPk(BirthDeathReportResident.Pk pk);
}
