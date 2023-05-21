package com.nhnacademy.resident.service.family_relationship;

import com.nhnacademy.resident.domain.family_relationship.RegisterFamilyRelationDto;
import com.nhnacademy.resident.domain.family_relationship.FamilyRelationDto;

public interface FamilyRelationshipService {
    FamilyRelationDto addFamilyRelationship(Long serialNumber, RegisterFamilyRelationDto dto);

    void delete(Long serialNumber, Long familySerialNumber);

    FamilyRelationDto update(Long serialNumber, Long familySerialNumber, String relationShip);
}
