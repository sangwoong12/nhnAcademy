package com.nhnacademy.springjpa.entity;

import java.util.List;
import java.util.Objects;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

// TODO #1 : `@NamedEntityGraph` 설정하시오
@NamedEntityGraphs({
        @NamedEntityGraph(name = "MemberWithMemberDetailsAndLocker", attributeNodes = {
                @NamedAttributeNode("memberDetails") , @NamedAttributeNode("locker")
        })
})
@Getter
@Setter
@Entity
@Table(name = "Members")
public class Member implements Persistable<String> {
    @Id
    @Column(name = "member_id")
    private String id;

    @Column(name = "user_name")
    private String userName;

    @OneToOne(mappedBy = "member")
    private Locker locker;

    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
    private List<MemberDetail> memberDetails;

    @Transient
    boolean shouldBeNew = false;

    @Override
    public boolean isNew() {
        return shouldBeNew || Objects.isNull(id);
    }

}
