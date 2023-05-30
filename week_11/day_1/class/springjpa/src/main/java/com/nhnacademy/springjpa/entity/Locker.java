package com.nhnacademy.springjpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Lockers")
public class Locker {
    @Id
    @Column(name = "locker_id")
    private Long id;

    @Column(name = "locker_name")
    private String name;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
