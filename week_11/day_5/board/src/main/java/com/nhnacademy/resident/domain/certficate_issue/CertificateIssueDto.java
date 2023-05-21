package com.nhnacademy.resident.domain.certficate_issue;

import com.nhnacademy.resident.entity.Resident;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CertificateIssueDto {
    Long getCertificateConfirmationNumber();
    LocalDate getCertificateIssueDate();

    ResidentDto getResident();

    interface ResidentDto {
        String getName();

        Resident.Gender getGender();

        LocalDateTime getBirthDate();

        String getResidentRegistrationNumber();
        String getRegistrationBaseAddress();

        List<FamilyRelationshipDto> getFamilyRelationships();

        interface FamilyRelationshipDto {
            String getFamilyRelationshipCode();

            TargetResidentDto getFamilyResident();

            interface TargetResidentDto {
                String getName();

                Resident.Gender getGender();

                LocalDateTime getBirthDate();

                String getResidentRegistrationNumber();
            }
        }
    }

}
