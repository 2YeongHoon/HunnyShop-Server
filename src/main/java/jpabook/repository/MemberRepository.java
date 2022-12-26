package jpabook.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpabook.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Member member){
        entityManager.persist(member);
    }

    public Member findOne(Long id){
        return entityManager.find(Member.class, id);
    }

    public List<Member> findAll(Long id){
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
