package jpabook.repository.order.query;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import jpabook.repository.order.query.dto.OrderItemQueryDto;
import jpabook.repository.order.query.dto.OrderQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

  private final EntityManager entityManager;

  public List<OrderQueryDto> findOrderQueryDtos() {
    List<OrderQueryDto> result = findOrders();

    result.forEach(o-> {
          List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId());
          o.setOrderItems(orderItems);
        });

    return result;
  }

  public List<OrderQueryDto> findAllByDto() {
    List<OrderQueryDto> result = findOrders();

    List<Long> orderIds = result.stream()
        .map(o -> o.getOrderId())
        .collect(Collectors.toList());

    List<OrderItemQueryDto> orderItems = entityManager.createQuery(
            "select new jpabook.repository.order.query.dto.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)"+
                " from OrderItem oi" +
                " join oi.item i" +
                " where oi.order.id in :orderIds", OrderItemQueryDto.class)
        .setParameter("orderIds", orderIds)
        .getResultList();

    Map<Long, List<OrderItemQueryDto>> orderItemMap = orderItems.stream()
        .collect(Collectors.groupingBy(orderItemQueryDto -> orderItemQueryDto.getOrderId()));

    result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));

    return result;
  }

  private List<OrderItemQueryDto> findOrderItems(Long orderId) {
    return entityManager.createQuery(
        "select new jpabook.repository.order.query.dto.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)"+
            " from OrderItem oi" +
            " join oi.item i" +
            " where oi.order.id = :orderId", OrderItemQueryDto.class)
        .setParameter("orderId", orderId)
        .getResultList();
  }

  private List<OrderQueryDto> findOrders() {
    return entityManager.createQuery(
        "select new jpabook.repository.order.query.dto.OrderQueryDto(o.id, m.userName, o.orderDate, o.status, d.address)"
            +
            " from Order o" +
            " join o.member m" +
            " join o.delivery d", OrderQueryDto.class
    ).getResultList();
  }

}
