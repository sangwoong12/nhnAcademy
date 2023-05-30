package com.nhnacademy.springjpa.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

@Getter
@Setter
@Entity
@Table(name = "MemberDetails")
public class MemberDetail implements Persistable<Long> {
    @Id
    @Column(name = "member_detail_id")
    private Long id;

    private String type;

    private String description;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Transient
    private boolean shouldBeNew = false;

    @Override
    public boolean isNew() {
        return shouldBeNew || Objects.isNull(id);
    }

}
