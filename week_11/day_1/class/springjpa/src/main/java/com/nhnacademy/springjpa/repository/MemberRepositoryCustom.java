package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Member;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface MemberRepositoryCustom {
    List<Member> getMembersWithAssociation();

}
