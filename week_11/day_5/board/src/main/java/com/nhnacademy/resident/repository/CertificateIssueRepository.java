package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.domain.certficate_issue.*;
import com.nhnacademy.resident.entity.CertificateIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue,Long> {
    //    @EntityGraph("ResidentWithFamilyRelationships")
    CertificateIssueDto findByCertificateConfirmationNumber(Long certificateConfirmationNumber);

    List<CertificateIssueListDto> findAllByResidentResidentSerialNumber(Long residentSerialNumber);

    @Query(value = "select c.certificate_issue_date as certificateIssueDate, " +
            "c.certificate_confirmation_number as certificateConfirmationNumber," +
            "h.household_composition_date as householdCompositionDate, " +
            "h.household_composition_reason_code as householdCompositionReasonCode, " +
            "r2.name as name " +
            "from certificate_issue c " +
            "inner join resident r on c.resident_serial_number = r.resident_serial_number " +
            "inner join household h on household_serial_number = h.household_serial_number " +
            "inner join resident r2 on h.household_resident_serial_number = r2.resident_serial_number " +
            "WHERE certificate_confirmation_number = :certificateConfirmationNumber", nativeQuery = true)
    CertificateTopDto getResidentCertificateTop(@Param("certificateConfirmationNumber") Long certificateConfirmationNumber);

    @Query(value = "select house_movement_report_date as houseMovementReportDate, " +
            "house_movement_address as houseMovementAddress, " +
            "last_address_yn as lastAddressYn " +
            "from certificate_issue c " +
            "inner join resident r on c.resident_serial_number = r.resident_serial_number " +
            "inner join household_composition_resident hcr on r.resident_serial_number = hcr.resident_serial_number " +
            "inner join household_movement_address hma on hcr.household_serial_number = hma.household_serial_number " +
            "WHERE certificate_confirmation_number = :certificateConfirmationNumber order by house_movement_report_date desc ", nativeQuery = true)
    List<CertificateMiddleDto> getResidentCertificateMiddle(@Param("certificateConfirmationNumber") Long certificateConfirmationNumber);

    @Query(value = "select " +
            "       hcr.household_relationship_code as householdRelationshipCode," +
            "       r2.name as name," +
            "       r2.resident_registration_number as residentRegistrationNumber," +
            "       hcr.report_date as reportDate," +
            "       hcr.household_composition_change_reason_code as householdCompositionChangeReasonCode " +
            "from" +
            "             household_composition_resident hcr" +
            "         inner join resident r2 on hcr.resident_serial_number = r2.resident_serial_number " +
            "WHERE household_serial_number = (" +
            "select hcr.household_serial_number from" +
            "             certificate_issue" +
            "         inner join resident r on certificate_issue.resident_serial_number = r.resident_serial_number" +
            "         inner join household_composition_resident hcr on r.resident_serial_number = hcr.resident_serial_number " +
            "WHERE certificate_confirmation_number = :certificateConfirmationNumber)", nativeQuery = true)
    List<CertificateBottomDto> getResidentCertificateBottom(@Param("certificateConfirmationNumber") Long certificateConfirmationNumber);

}
