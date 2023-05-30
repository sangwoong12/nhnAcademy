package com.nhnacademy.springjpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "BoardDetails")
public class BoardDetail {
    @Id
    private Long boardId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "board_id")
    private Board board;

    private String content;

}
