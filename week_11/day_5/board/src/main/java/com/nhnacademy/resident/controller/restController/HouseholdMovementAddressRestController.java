package com.nhnacademy.resident.controller.restController;

import com.nhnacademy.resident.domain.household_movement_address.HouseholdMovementAddressDto;
import com.nhnacademy.resident.domain.household_movement_address.RegisterHouseholdMovementAddressDto;
import com.nhnacademy.resident.domain.household_movement_address.UpdateHouseholdMovementAddressDto;
import com.nhnacademy.resident.service.household_movement_address.HouseholdMovementAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/household/{householdSerialNumber}/movement")
public class HouseholdMovementAddressRestController {
    HouseholdMovementAddressService householdMovementAddressService;

    public HouseholdMovementAddressRestController(HouseholdMovementAddressService householdMovementAddressService) {
        this.householdMovementAddressService = householdMovementAddressService;
    }

    @PostMapping
    public ResponseEntity<HouseholdMovementAddressDto> register(@PathVariable Long householdSerialNumber, @RequestBody RegisterHouseholdMovementAddressDto dto) {
        return ResponseEntity.status(200).body(householdMovementAddressService.addHouseholdMovementAddress(householdSerialNumber,dto));
    }

    @PutMapping("/{reportDate}")
    public ResponseEntity<HouseholdMovementAddressDto> modify(@PathVariable Long householdSerialNumber, @PathVariable String reportDate, @RequestBody UpdateHouseholdMovementAddressDto dto) {
        return ResponseEntity.status(200).body(householdMovementAddressService.update(householdSerialNumber,reportDate,dto));
    }

    @DeleteMapping("/{reportDate}")
    public ResponseEntity<?> delete(@PathVariable Long householdSerialNumber, @PathVariable String reportDate) {
        householdMovementAddressService.delete(householdSerialNumber,reportDate);
        return ResponseEntity.status(200).build();
    }
}
