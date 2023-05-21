package com.nhnacademy.resident.controller.restController;

import com.nhnacademy.resident.domain.birth_death_report_resident.RegisterReportResidentDto;
import com.nhnacademy.resident.domain.birth_death_report_resident.ReportResidentDto;
import com.nhnacademy.resident.domain.birth_death_report_resident.UpdateReportResidentDto;
import com.nhnacademy.resident.entity.BirthDeathReportResident;
import com.nhnacademy.resident.service.birth_report_resident.BirthDeathReportResidentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residents/{serialNumber}/death")
public class DeathRestController {
    BirthDeathReportResidentService birthDeathReportResidentService;

    public DeathRestController(BirthDeathReportResidentService birthDeathReportResidentService) {
        this.birthDeathReportResidentService = birthDeathReportResidentService;
    }

    @PostMapping
    private ResponseEntity<ReportResidentDto> register(@PathVariable Long serialNumber, @RequestBody RegisterReportResidentDto dto) {
        return ResponseEntity.status(200).body(birthDeathReportResidentService.addReportResident(serialNumber, dto, BirthDeathReportResident.BirthDeathType.사망));
    }

    @PutMapping("/{targetSerialNumber}")
    private ResponseEntity<ReportResidentDto> modify(@PathVariable Long serialNumber, @PathVariable Long targetSerialNumber, @RequestBody UpdateReportResidentDto dto) {
        return ResponseEntity.status(200).body(birthDeathReportResidentService.update(serialNumber, targetSerialNumber, dto, BirthDeathReportResident.BirthDeathType.사망));
    }

    @DeleteMapping("/{targetSerialNumber}")
    private ResponseEntity<?> delete(@PathVariable Long serialNumber, @PathVariable Long targetSerialNumber) {
        birthDeathReportResidentService.delete(serialNumber, targetSerialNumber, BirthDeathReportResident.BirthDeathType.사망);
        return ResponseEntity.status(200).build();
    }
}
