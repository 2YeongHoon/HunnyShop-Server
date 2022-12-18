package jpabook.member.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpabook.member.entity.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(Member member){
        entityManager.persist(member);
        return member.getId();
    }

    public Member find(Long id){
        return entityManager.find(Member.class, id);
    }
}
