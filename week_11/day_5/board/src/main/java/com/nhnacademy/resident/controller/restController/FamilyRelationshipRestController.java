package com.nhnacademy.resident.controller.restController;

import com.nhnacademy.resident.domain.family_relationship.RegisterFamilyRelationDto;
import com.nhnacademy.resident.domain.family_relationship.UpdateFamilyRelationDto;
import com.nhnacademy.resident.domain.family_relationship.FamilyRelationDto;
import com.nhnacademy.resident.service.family_relationship.FamilyRelationshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residents/{serialNumber}/relationship")
public class FamilyRelationshipRestController {
    FamilyRelationshipService familyRelationshipService;

    public FamilyRelationshipRestController(FamilyRelationshipService familyRelationshipService) {
        this.familyRelationshipService = familyRelationshipService;
    }

    @PostMapping
    public ResponseEntity<FamilyRelationDto> register(@PathVariable Long serialNumber, @RequestBody RegisterFamilyRelationDto dto) {
        return ResponseEntity.status(200).body(familyRelationshipService.addFamilyRelationship(serialNumber, dto));
    }

    @PutMapping("/{familySerialNumber}")
    public ResponseEntity<FamilyRelationDto> modify(@PathVariable Long serialNumber, @PathVariable Long familySerialNumber, @RequestBody UpdateFamilyRelationDto relationShip) {
        return ResponseEntity.status(200).body(familyRelationshipService.update(serialNumber, familySerialNumber, relationShip.getRelationShip()));
    }

    @DeleteMapping("/{familySerialNumber}")
    public ResponseEntity<?> delete(@PathVariable Long serialNumber, @PathVariable Long familySerialNumber) {
        familyRelationshipService.delete(serialNumber, familySerialNumber);
        return ResponseEntity.status(200).build();
    }
}
