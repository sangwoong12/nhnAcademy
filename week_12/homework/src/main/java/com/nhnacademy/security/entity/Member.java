package com.nhnacademy.security.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Members")
@Getter
@Setter
public class Member {
    @Id
    @Column(name = "member_id")
    private String memberId;
    private String name;
    private String pwd;

    @OneToOne(mappedBy = "member")
    private Authority authority;
}
