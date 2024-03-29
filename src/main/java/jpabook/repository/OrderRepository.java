package jpabook.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import jpabook.domain.Order;
import jpabook.domain.QMember;
import jpabook.domain.QOrder;
import jpabook.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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

  public List<Order> findAll() {
    return entityManager.createQuery("select o from Order o", Order.class)
        .getResultList();
  }

  public List<Order> findAllByString(OrderSearch orderSearch) {

    String jpql = "select o from Order o join o.member m";
    boolean isFirstCondition = true;

    //주문 상태 검색
    if (orderSearch.getOrderStatus() != null) {
      if (isFirstCondition) {
        jpql += " where";
        isFirstCondition = false;
      } else {
        jpql += " and";
      }
      jpql += " o.status = :status";
    }

    //회원 이름 검색
    if (StringUtils.hasText(orderSearch.getMemberName())) {
      if (isFirstCondition) {
        jpql += " where";
        isFirstCondition = false;
      } else {
        jpql += " and";
      }
      jpql += " m.userName like :name";
    }

    TypedQuery<Order> query = entityManager.createQuery(jpql, Order.class)
        .setMaxResults(1000);

    if (orderSearch.getOrderStatus() != null) {
      query = query.setParameter("status", orderSearch.getOrderStatus());
    }
    if (StringUtils.hasText(orderSearch.getMemberName())) {
      query = query.setParameter("name", orderSearch.getMemberName());
    }

    return query.getResultList();
  }

  public List<Order> findAll(OrderSearch orderSearch){
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    QOrder order = QOrder.order;
    QMember member = QMember.member;

    return queryFactory
        .select(order)
        .from(order)
        .join(order.member, member)
        .where(
            statsEq(orderSearch.getOrderStatus()),
            nameLike(orderSearch.getMemberName()))
        .limit(1000)
        .fetch();
  }

  private BooleanExpression nameLike(String memberName){
    if(!StringUtils.hasText(memberName)){
      return null;
    }

    return QMember.member.userName.like(memberName);
  }

  private BooleanExpression statsEq(OrderStatus orderStatus){
    if(orderStatus == null){
      return null;
    }

    return QOrder.order.status.eq(orderStatus);
  }

  public List<Order> findAllWithMemberDelivery() {
    return entityManager.createQuery(
        "select o from Order o" +
            " join fetch o.member m" +
            " join fetch o.delivery d", Order.class
    ).getResultList();
  }

  public List<Order> findAllWithItem() {
    return entityManager.createQuery(
        "select distinct o from Order o" +
            " join fetch o.member m" +
            " join fetch o.delivery d" +
            " join fetch o.orderItems oi" +
            " join fetch oi.item i", Order.class
    ).getResultList();
  }
}
