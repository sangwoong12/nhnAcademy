package com.nhnacademy.resident.service.certificate_issue;

import com.nhnacademy.resident.domain.certficate_issue.*;
import com.nhnacademy.resident.repository.CertificateIssueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("certificateIssueService")
public class CertificateIssueServiceImpl implements CertificateIssueService {
    CertificateIssueRepository certificateIssueRepository;

    public CertificateIssueServiceImpl(CertificateIssueRepository certificateIssueRepository) {
        this.certificateIssueRepository = certificateIssueRepository;
    }
    public CertificateIssueDto getResidentRegistrationCertificate(Long certificateConfirmationNumber){
        return certificateIssueRepository.findByCertificateConfirmationNumber(certificateConfirmationNumber);
    }
    public List<CertificateIssueListDto> getCertificateIssueList(Long serialNumber){
        return certificateIssueRepository.findAllByResidentResidentSerialNumber(serialNumber);
    }
    public CertificateTopDto getResidentCertificateTop(Long certificateConfirmationNumber){
        return certificateIssueRepository.getResidentCertificateTop(certificateConfirmationNumber);
    }
    public List<CertificateMiddleDto> getResidentCertificateMiddle(Long certificateConfirmationNumber){
        return certificateIssueRepository.getResidentCertificateMiddle(certificateConfirmationNumber);
    }
    public List<CertificateBottomDto> getResidentCertificateBottom(Long certificateConfirmationNumber){
        return certificateIssueRepository.getResidentCertificateBottom(certificateConfirmationNumber);
    }
}
