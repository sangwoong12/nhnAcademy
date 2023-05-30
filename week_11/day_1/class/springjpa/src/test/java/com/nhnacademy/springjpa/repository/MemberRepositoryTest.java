package com.nhnacademy.springjpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.springjpa.config.RootConfig;
import com.nhnacademy.springjpa.config.WebConfig;
import com.nhnacademy.springjpa.domain.MemberDto;
import com.nhnacademy.springjpa.domain.MemberNameOnly;
import com.nhnacademy.springjpa.entity.Member;
import com.nhnacademy.springjpa.entity.MemberDetail;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
    @ContextConfiguration(classes = RootConfig.class),
    @ContextConfiguration(classes = WebConfig.class)
})
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    void test() {
        Member member = new Member();
        member.setId("member1");
        member.setUserName("nhn");

        memberRepository.saveAndFlush(member);

        MemberNameOnly memberNameOnly = memberRepository.queryById("member1");
        assertThat(memberNameOnly.getUserName()).isEqualTo("nhn");
    }

    @Test
    void test2() {
        Member member = new Member();
        member.setId("member1");
        member.setUserName("nhn");
        member.setShouldBeNew(true);

        MemberDetail memberDetail1 = new MemberDetail();
        memberDetail1.setId(1L);
        memberDetail1.setType("habit");
        memberDetail1.setDescription("play");
        memberDetail1.setMember(member);
        memberDetail1.setShouldBeNew(true);

        MemberDetail memberDetail2 = new MemberDetail();
        memberDetail2.setId(2L);
        memberDetail2.setType("score");
        memberDetail2.setDescription("good");
        memberDetail2.setMember(member);
        memberDetail2.setShouldBeNew(true);

        List<MemberDetail> details = Arrays.asList(memberDetail1, memberDetail2);

        member.setMemberDetails(details);

        memberRepository.saveAndFlush(member);

        List<MemberDto> dtos = memberRepository.findByUserName("nhn");
        assertThat(dtos).hasSize(1);
        assertThat(dtos.get(0).getMemberDetails()).hasSize(2);
    }

    @Test
    void test3() {
        memberRepository.getMembersWithAssociation()
            .stream()
            .map(Member::getMemberDetails)
            .flatMap(Collection::stream)
            .map(MemberDetail::getType)
            .collect(Collectors.toList());
    }

    // TODO #3: test case를 실행했을 때 N + 1 문제가 발생하지 않도록 하세요.
    @Test
    void test4() {
        memberRepository.readAllBy();
    }

}
