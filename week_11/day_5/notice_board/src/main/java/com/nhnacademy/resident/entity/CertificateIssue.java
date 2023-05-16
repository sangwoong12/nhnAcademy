package com.nhnacademy.resident.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "certificate_issue")
@Entity
public class CertificateIssue {
    @Id
    private Long certificateConfirmationNumber;

    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;
    private String certificateTypeCode;
    private LocalDate certificateIssueDate;
}
