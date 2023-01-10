package jpabook.service;

import jpabook.domain.Delivery;
import jpabook.domain.Member;
import jpabook.domain.Order;
import jpabook.domain.OrderItem;
import jpabook.domain.item.Item;
import jpabook.repository.ItemRepository;
import jpabook.repository.MemberRepository;
import jpabook.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

  private final MemberRepository memberRepository;
  private final ItemRepository itemRepository;
  private final OrderRepository orderRepository;

  /**
   * 주문
   */
  public Long order(Long memberId, Long itemId, int count){

    // 엔티티 조회
    Member member = memberRepository.findOne(memberId);
    Item item = itemRepository.find(itemId);

    // 배송정보 생성
    Delivery delivery = new Delivery();
    delivery.setAddress(member.getAddress());

    // 주문상품 생성
    OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

    // 주문 생성
    Order order = Order.createOrder(member, delivery, orderItem);
    orderRepository.save(order);

    return order.getId();
  }

  /**
   * 주문 취소
   */
  @Transactional
  public void cancelOrder(Long orderId){
    Order order = orderRepository.findOne(orderId);
    order.cancel();
  }
}
