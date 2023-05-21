package com.nhnacademy.resident.domain.certficate_issue;

import java.time.LocalDate;


public interface CertificateTopDto {
    LocalDate getCertificateIssueDate();

    Long getCertificateConfirmationNumber();

    LocalDate getHouseholdCompositionDate();

    String getHouseholdCompositionReasonCode();

    String getName();
}
