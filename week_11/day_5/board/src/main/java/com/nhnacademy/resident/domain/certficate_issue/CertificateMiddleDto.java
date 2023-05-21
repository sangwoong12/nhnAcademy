package com.nhnacademy.resident.domain.certficate_issue;

import java.time.LocalDate;


public interface CertificateMiddleDto {
    LocalDate getHouseMovementReportDate();

    String getHouseMovementAddress();

    String getLastAddressYn();
}
