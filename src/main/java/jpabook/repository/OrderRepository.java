package jpabook.repository;

import javax.persistence.EntityManager;
import jpabook.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

  private final EntityManager entityManager;

  public void save(Order order){
    entityManager.persist(order);
  }

  public Order findOne(Long id){
    return entityManager.find(Order.class, id);
  }
}
