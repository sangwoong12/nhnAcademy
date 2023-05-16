package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportResidentRepository extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.Pk> {
    Optional<BirthDeathReportResident> getAllBy();
}
