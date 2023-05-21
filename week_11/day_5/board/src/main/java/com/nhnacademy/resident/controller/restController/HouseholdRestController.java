package com.nhnacademy.resident.controller.restController;

import com.nhnacademy.resident.domain.household.HouseholdDto;
import com.nhnacademy.resident.domain.household.RegisterHouseholdDto;
import com.nhnacademy.resident.service.household.HouseholdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/household")
public class HouseholdRestController {
    HouseholdService householdService;

    public HouseholdRestController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @PostMapping
    public ResponseEntity<HouseholdDto> register(@RequestBody RegisterHouseholdDto dto) {
        return ResponseEntity.status(200).body(householdService.addHousehold(dto));
    }

    @DeleteMapping("/{householdSerialNumber}")
    public ResponseEntity<?> delete(@PathVariable Long householdSerialNumber) {
        householdService.remove(householdSerialNumber);
        return ResponseEntity.status(200).build();
    }
}
