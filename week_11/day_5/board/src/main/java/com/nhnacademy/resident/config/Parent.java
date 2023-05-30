package com.nhnacademy.resident.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(ParentId.class)
public class Parent {
    @Id
    @Column(name="PARENT_ID1")
    private String id1;

    @Id
    @Column(name="PARENT_ID2")
    private String id2;
    private String name;
}
@NoArgsConstructor
@AllArgsConstructor
class ParentId implements Serializable {
    private String id1;
    private String id2;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
@Entity
class Child {
    @Id
    private String id;

    @ManyToOne
    @JoinColumns(@JoinColumn(name = "PARENT_ID1"), @JoinColumn(name = "PARENT_ID1"))
    private Parent parent;
}
