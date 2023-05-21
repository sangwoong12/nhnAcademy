package com.nhnacademy.resident.domain.certficate_issue;

import java.time.LocalDate;

public interface CertificateBottomDto {
    String getHouseholdRelationshipCode();

    String getName();

    String getResidentRegistrationNumber();

    LocalDate getReportDate();

    String getHouseholdCompositionChangeReasonCode();
}
