package jpabook.repository.order.simplequery;

import java.util.List;
import javax.persistence.EntityManager;
import jpabook.repository.order.simplequery.dto.OrderSimpleQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

  private final EntityManager entityManager;

  public List<OrderSimpleQueryDto> findOrderDtos() {
    return entityManager.createQuery(
        "select new jpabook.repository.order.simplequery.dto.OrderSimpleQueryDto("
            + "o.id, m.userName, o.orderDate, o.status, d.address)" +
            " from Order o" +
            " join o.member m" +
            " join o.delivery d", OrderSimpleQueryDto.class
    ).getResultList();
  }

}
