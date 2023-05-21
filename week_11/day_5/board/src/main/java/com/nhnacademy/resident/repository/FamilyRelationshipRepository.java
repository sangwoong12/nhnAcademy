package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.domain.family_relationship.FamilyRelationshipDto;
import com.nhnacademy.resident.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk> {

    @Modifying
    @Query(value = "INSERT INTO family_relationship (base_resident_serial_number, family_resident_serial_number, family_relationship_code) VALUES (:serialNumber, :familySerialNumber, :relationship)", nativeQuery = true)
    void save(@Param("serialNumber") Long serialNumber, @Param("familySerialNumber") Long familySerialNumber, @Param("relationship") String relationship);

    FamilyRelationshipDto findByPk(FamilyRelationship.Pk pk);

}
