package com.nhnacademy.resident.entity;

import javax.persistence.*;
import java.time.LocalDate;

@NamedEntityGraphs(value = {
        @NamedEntityGraph(name = "certificateIssueWithResidentAndFamilyRelationship", attributeNodes = {
                @NamedAttributeNode(value = "resident", subgraph = "resident")
        }, subgraphs = {
                @NamedSubgraph(name = "resident", attributeNodes = {
                        @NamedAttributeNode("familyRelationships")
                })
        })
})
@Table(name = "certificate_issue")
@Entity
public class CertificateIssue {
    @Id
    @Column(name = "certificate_confirmation_number")
    private Long certificateConfirmationNumber;

    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;
    @Column(name = "certificate_type_code")
    private String certificateTypeCode;
    @Column(name = "certificate_issue_date")
    private LocalDate certificateIssueDate;
}
