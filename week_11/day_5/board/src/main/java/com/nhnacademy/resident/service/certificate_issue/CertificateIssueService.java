package com.nhnacademy.resident.service.certificate_issue;

import com.nhnacademy.resident.domain.certficate_issue.*;

import java.util.List;

public interface CertificateIssueService {
    CertificateIssueDto getResidentRegistrationCertificate(Long certificateConfirmationNumber);
    List<CertificateIssueListDto> getCertificateIssueList(Long serialNumber);

    CertificateTopDto getResidentCertificateTop(Long certificateConfirmationNumber);
    List<CertificateMiddleDto> getResidentCertificateMiddle(Long certificateConfirmationNumber);
    List<CertificateBottomDto> getResidentCertificateBottom(Long certificateConfirmationNumber);
}
