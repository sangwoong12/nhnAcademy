package com.nhnacademy.resident.utils;

import com.nhnacademy.resident.entity.FamilyRelationship;

public class FamilyRelationshipUtils {
    private FamilyRelationshipUtils() {
    }

    public static FamilyRelationship create(Long serialNumber, Long familySerialNumber, String relationship) {
        FamilyRelationship familyRelationship = new FamilyRelationship();
        familyRelationship.setPk(createPk(serialNumber, familySerialNumber));
        familyRelationship.setFamilyRelationshipCode(relationship);
        return familyRelationship;
    }

    public static FamilyRelationship.Pk createPk(Long serialNumber, Long familySerialNumber) {
        FamilyRelationship.Pk pk = new FamilyRelationship.Pk();
        pk.setBaseResidentSerialNumber(serialNumber);
        pk.setFamilyResidentSerialNumber(familySerialNumber);
        return pk;
    }
}
