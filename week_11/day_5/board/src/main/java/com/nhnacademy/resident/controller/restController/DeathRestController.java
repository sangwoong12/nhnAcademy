package com.nhnacademy.resident.controller.restController;

import com.nhnacademy.resident.domain.DeleteResponse;
import com.nhnacademy.resident.domain.birth_death_report_resident.RegisterReportResidentDto;
import com.nhnacademy.resident.domain.birth_death_report_resident.ReportResidentDto;
import com.nhnacademy.resident.domain.birth_death_report_resident.UpdateReportResidentDto;
import com.nhnacademy.resident.entity.BirthDeathReportResident;
import com.nhnacademy.resident.service.birth_report_resident.BirthDeathReportResidentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/residents/{serialNumber}/death")
public class DeathRestController {
    BirthDeathReportResidentService birthDeathReportResidentService;

    public DeathRestController(BirthDeathReportResidentService birthDeathReportResidentService) {
        this.birthDeathReportResidentService = birthDeathReportResidentService;
    }

    @PostMapping
    private ResponseEntity<ReportResidentDto> register(@PathVariable Long serialNumber, @RequestBody @Valid RegisterReportResidentDto dto) {
        return ResponseEntity.status(200).body(birthDeathReportResidentService.addReportResident(serialNumber, dto, BirthDeathReportResident.BirthDeathType.DEATH));
    }

    @PutMapping("/{targetSerialNumber}")
    private ResponseEntity<ReportResidentDto> modify(@PathVariable Long serialNumber, @PathVariable Long targetSerialNumber, @RequestBody @Valid UpdateReportResidentDto dto) {
        return ResponseEntity.status(200).body(birthDeathReportResidentService.update(serialNumber, targetSerialNumber, dto, BirthDeathReportResident.BirthDeathType.DEATH));
    }

    @DeleteMapping("/{targetSerialNumber}")
    private ResponseEntity<DeleteResponse> delete(@PathVariable Long serialNumber, @PathVariable Long targetSerialNumber) {
        birthDeathReportResidentService.delete(serialNumber, targetSerialNumber, BirthDeathReportResident.BirthDeathType.DEATH);
        return ResponseEntity.status(200).body(new DeleteResponse(Map.of("serialNumber", String.valueOf(serialNumber)), true));
    }
}
