package com.nhnacademy.resident.controller.restController;

import com.nhnacademy.resident.domain.DeleteResponse;
import com.nhnacademy.resident.domain.birth_death_report_resident.RegisterReportResidentDto;
import com.nhnacademy.resident.domain.birth_death_report_resident.ReportResidentDto;
import com.nhnacademy.resident.domain.birth_death_report_resident.UpdateReportResidentDto;
import com.nhnacademy.resident.entity.BirthDeathReportResident;
import com.nhnacademy.resident.service.birth_report_resident.BirthDeathReportResidentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/residents/{serialNumber}/birth")
public class BirthRestController {
    BirthDeathReportResidentService birthDeathReportResidentService;

    public BirthRestController(BirthDeathReportResidentService birthDeathReportResidentService) {
        this.birthDeathReportResidentService = birthDeathReportResidentService;
    }

    @PostMapping
    public ResponseEntity<ReportResidentDto> register(@PathVariable Long serialNumber, @RequestBody RegisterReportResidentDto dto) {
        return ResponseEntity.status(200).body(birthDeathReportResidentService.addReportResident(serialNumber, dto, BirthDeathReportResident.BirthDeathType.BIRTH));
    }

    @PutMapping("/{targetSerialNumber}")
    public ResponseEntity<ReportResidentDto> modify(@PathVariable Long serialNumber, @PathVariable Long targetSerialNumber, @RequestBody UpdateReportResidentDto dto) {
        return ResponseEntity.status(200).body(birthDeathReportResidentService.update(serialNumber, targetSerialNumber, dto, BirthDeathReportResident.BirthDeathType.BIRTH));
    }

    @DeleteMapping("/{targetSerialNumber}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable Long serialNumber, @PathVariable Long targetSerialNumber) {
        birthDeathReportResidentService.delete(serialNumber, targetSerialNumber, BirthDeathReportResident.BirthDeathType.BIRTH);
        return ResponseEntity.status(200)
                .body(new DeleteResponse(Map.of("serialNumber", String.valueOf(serialNumber)), true));
    }

}
