package com.nhnacademy.resident.controller.restController;

import com.nhnacademy.resident.domain.resident.RegisterResidentDto;
import com.nhnacademy.resident.domain.resident.ResidentDto;
import com.nhnacademy.resident.domain.resident.ResidentPageDto;
import com.nhnacademy.resident.entity.BirthDeathReportResident;
import com.nhnacademy.resident.service.resident.ResidentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/residents")
public class ResidentRestController {
    ResidentService residentService;

    public ResidentRestController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @PostMapping
    public ResponseEntity<ResidentDto> register(@RequestBody RegisterResidentDto residentDto) {
        return ResponseEntity.status(200).body(residentService.addResident(residentDto));
    }

    @PutMapping("/{serialNumber}")
    public ResponseEntity<ResidentDto> modify(@PathVariable Long serialNumber, @RequestBody RegisterResidentDto residentDto) {
        return ResponseEntity.status(200).body(residentService.modify(residentDto, serialNumber));
    }
}
