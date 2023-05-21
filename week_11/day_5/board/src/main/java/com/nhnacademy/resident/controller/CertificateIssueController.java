package com.nhnacademy.resident.controller;

import com.nhnacademy.resident.domain.certficate_issue.*;
import com.nhnacademy.resident.service.certificate_issue.CertificateIssueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/certificate-issue")
public class CertificateIssueController {
    CertificateIssueService certificateIssueService;

    public CertificateIssueController(CertificateIssueService certificateIssueService) {
        this.certificateIssueService = certificateIssueService;
    }

    @GetMapping("/family-relationship-certificate")
    public String getFamilyRelationshipCertificate(@RequestParam Long certificateConfirmationNumber, Model model) {
        CertificateIssueDto certificateIssue = certificateIssueService.getResidentRegistrationCertificate(certificateConfirmationNumber);
        model.addAttribute("certificateIssue", certificateIssue);
        return "family-relationship-certificate";
    }

    @GetMapping("/resident-registration-certificate")
    public String getResidentRegistrationCertificate(@RequestParam Long certificateConfirmationNumber, Model model) {
        CertificateTopDto top = certificateIssueService.getResidentCertificateTop(certificateConfirmationNumber);
        List<CertificateMiddleDto> middles = certificateIssueService.getResidentCertificateMiddle(certificateConfirmationNumber);
        List<CertificateBottomDto> bottoms = certificateIssueService.getResidentCertificateBottom(certificateConfirmationNumber);
        model.addAttribute("top", top);
        model.addAttribute("middles", middles);
        model.addAttribute("bottoms", bottoms);
        return "resident-registration-certificate";
    }

    @GetMapping
    public String getCertificateIssue(@RequestParam Long serialNumber, Model model) {
        List<CertificateIssueListDto> certificateIssueList = certificateIssueService.getCertificateIssueList(serialNumber);
        model.addAttribute("certificateIssueList", certificateIssueList);
        return "certificate-issue-list";
    }
}
