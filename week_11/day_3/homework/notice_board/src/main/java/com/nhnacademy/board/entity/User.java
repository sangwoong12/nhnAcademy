package com.nhnacademy.board.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "User")
@Setter
@Getter
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String id;

    private String password;

    private String name;

    @Column(name = "profile_file_name")
    private String profileFileName;

}
