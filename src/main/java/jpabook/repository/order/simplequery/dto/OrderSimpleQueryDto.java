package jpabook.repository.order.simplequery.dto;

import java.time.LocalDateTime;
import jpabook.domain.Address;
import jpabook.domain.Order;
import jpabook.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderSimpleQueryDto {

  private Long orderId;
  private String name;
  private LocalDateTime orderDate;
  private OrderStatus orderStatus;
  private Address address;

  public OrderSimpleQueryDto(Long orderId, String name, LocalDateTime orderDate,
      OrderStatus orderStatus, Address address) {
    this.orderId = orderId;
    this.name = name;
    this.orderDate = orderDate;
    this.orderStatus = orderStatus;
    this.address = address;
  }

  public OrderSimpleQueryDto(Order order) {
    orderId = order.getId();
    name = order.getMember().getUserName();
    orderDate = order.getOrderDate();
    orderStatus = order.getStatus();
    address = order.getDelivery().getAddress();
  }

}
