package jpabook.member.repository;

import javax.transaction.Transactional;
import jpabook.domain.Member;
import jpabook.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

//TODO 레거시 코드 변경으로 인해 테스트 코드 임시 주석처리.
//@SpringBootTest
//class MemberRepositoryTest {
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void testMember() throws Exception{
//        // given
//        Member member = new Member();
//        member.setUserName("hunny");
//
//        // when
////        Long saveId = memberRepository.save(member);
////        Member findMember = memberRepository.find(saveId);
//
//        // then
//        Assertions.assertThat(member.getId()).isEqualTo(findMember.getId());
//        Assertions.assertThat(member.getUserName()).isEqualTo(findMember.getUserName());
//    }
//
//    @Test
//    void find() {
//    }
//}