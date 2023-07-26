package jpabook.restapi;

import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.util.List;
import jpabook.domain.Address;
import jpabook.domain.Order;
import jpabook.enums.OrderStatus;
import jpabook.repository.OrderRepository;
import jpabook.repository.OrderSearch;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * XToOne(ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

  private final OrderRepository orderRepository;

  /**
   * V1. 엔티티 직접 노출
   * - Hibernate5Module 모듈 등록, LAZY=null 처리
   * - 양방향 관계 문제 발생 -> @JsonIgnore
   */
  @GetMapping(value = "api/v1/simple-order")
  public List<Order> orderV1(){
    List<Order> all = orderRepository.findAllByString(new OrderSearch());
    for(Order order: all){
      order.getMember().getUserName(); // Lazy 강제 초기화
      order.getDelivery().getAddress(); // Lazy 강제초기화
    }

    return all;
  }

  /**
   * V2. 엔티티를 조회해서 DTO로 변환(fetch join 사용X)
   * - 단점: 지연로딩으로 쿼리 N번 호출
   */
  @GetMapping("/api/v2/simple-orders")
  public List<SimpleOrderDto> ordersV2() {
    List<Order> orders = orderRepository.findAll();
    List<SimpleOrderDto> result = orders.stream()
        .map(o -> new SimpleOrderDto(o))
        .collect(toList());

    return result;
  }

  @Data
  static class SimpleOrderDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate; //주문시간
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderDto(Order order) {
      orderId = order.getId();
      name = order.getMember().getUserName();
      orderDate = order.getOrderDate();
      orderStatus = order.getStatus();
      address = order.getDelivery().getAddress();
    }
  }
}
