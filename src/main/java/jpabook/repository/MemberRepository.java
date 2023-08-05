package jpabook.repository;

import java.util.List;
import jpabook.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
  List<Member> findByUserName(String name);
}
