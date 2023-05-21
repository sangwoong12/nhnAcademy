package com.nhnacademy.resident.domain.family_relationship;

import com.nhnacademy.resident.entity.FamilyRelationship;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FamilyRelationDto {
    String familyRelationshipCode;
    Long familyResidentSerialNumber;
    Long baseResidentSerialNumber;
    public static FamilyRelationDto createByFamilyRelationDto(FamilyRelationshipDto dto){
        return new FamilyRelationDto(dto.getFamilyRelationshipCode(),dto.getPk().getFamilyResidentSerialNumber(),dto.getPk().getBaseResidentSerialNumber());
    }
    public static FamilyRelationDto createByEntity(FamilyRelationship fr){
        return new FamilyRelationDto(fr.getFamilyRelationshipCode(),fr.getPk().getFamilyResidentSerialNumber(), fr.getPk().getBaseResidentSerialNumber());
    }
}
