package com.nhnacademy.resident.domain.family_relationship;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RegisterFamilyRelationDto {
    private Long familySerialNumber;
    private String relationShip;
}
