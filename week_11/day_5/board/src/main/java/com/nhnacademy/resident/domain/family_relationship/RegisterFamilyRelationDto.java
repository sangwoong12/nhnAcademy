package com.nhnacademy.resident.domain.family_relationship;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class RegisterFamilyRelationDto {
    @NotNull
    private Long familySerialNumber;
    @NotNull
    private String relationShip;
}
