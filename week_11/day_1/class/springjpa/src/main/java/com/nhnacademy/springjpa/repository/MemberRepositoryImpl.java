package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Member;
import com.nhnacademy.springjpa.entity.QLocker;
import com.nhnacademy.springjpa.entity.QMember;
import com.nhnacademy.springjpa.entity.QMemberDetail;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom {
    public MemberRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public List<Member> getMembersWithAssociation() {
        QMember member = QMember.member;
        QLocker locker = QLocker.locker;
        QMemberDetail memberDetail = QMemberDetail.memberDetail;


        return from(member)
            .innerJoin(member.locker, locker).fetchJoin()
            .leftJoin(member.memberDetails, memberDetail).fetchJoin()
            .fetch();
    }

}
