package jpabook.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import jpabook.domain.Member;
import jpabook.repository.MemberRepositoryOld;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepositoryOld memberRepository;

    @Test
    void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setUserName("Lee");

        //when
        Long saveId = memberService.join(member);

        //then
        Assertions.assertThat(member.equals(memberRepository.findOne(saveId)));
    }

    @Test()
    void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setUserName("Lee");

        Member member2 = new Member();
        member2.setUserName("Lee");

        //when
        //then
        assertThrows(IllegalStateException.class, () ->
        {
            memberService.join(member1);
            memberService.join(member2);
        });
    }
}