package com.nhnacademy.resident.domain.family_relationship;


import com.nhnacademy.resident.entity.FamilyRelationship;

public interface FamilyRelationshipDto {
    String getFamilyRelationshipCode();

    FamilyRelationship.Pk getPk();

}