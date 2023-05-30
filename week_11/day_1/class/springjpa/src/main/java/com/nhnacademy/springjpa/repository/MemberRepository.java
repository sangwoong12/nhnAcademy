package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.MemberDto;
import com.nhnacademy.springjpa.domain.MemberNameOnly;
import com.nhnacademy.springjpa.entity.Member;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, String>, MemberRepositoryCustom {
    MemberNameOnly queryById(String id);

    List<MemberDto> findByUserName(String userName);

    Page<MemberNameOnly> getAllBy(Pageable pageable);

    // TODO #2: `@EntityGraph`를 이용해서 적용할 entity graph 지정
    @EntityGraph("MemberWithMemberDetailsAndLocker")
    List<Member> readAllBy();
}
