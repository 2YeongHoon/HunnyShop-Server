package jpabook.service;

import java.util.List;
import jpabook.domain.Member;
import jpabook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 등록
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByUserName(member.getUserName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미존재하는 아이디입니다.");
        }
    }

    /**
     * 회원 단건 조회
     */
    public Member findOne(Long memberId){
        return memberRepository.findById(memberId).get() ;

    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
}
