package com.nhnacademy.security.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Authoroties")
@Getter @Setter
public class Authority {
    @Id
    @Column(name = "member_id")
    private String memberId;

    private String authority;

    @OneToOne
    @MapsId
    @JoinColumn(name = "member_id")
    private Member member;
}
