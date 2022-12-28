package jpabook.repository;

import java.util.List;
import javax.persistence.EntityManager;
import jpabook.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager entityManager;

    public void save(Member member){
        entityManager.persist(member);
    }

    public Member findOne(Long id){
        return entityManager.find(Member.class, id);
    }

    public List<Member> findAll(){
        return entityManager.createQuery("select m from Member m", Member.class)
            .getResultList();
    }

    public List<Member> findByName(String name){
        return entityManager.createQuery(
            "select m from Member m where m.userName = :name", Member.class)
            .setParameter("name", name)
            .getResultList();
    }
}
