package jpabook.repository.order.query.dto;

import java.time.LocalDateTime;
import java.util.List;
import jpabook.domain.Address;
import jpabook.enums.OrderStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "orderId")
public class OrderQueryDto {

  private Long orderId;
  private String name;
  private LocalDateTime orderDate;
  private OrderStatus orderStatus;
  private Address address;
  private List<OrderItemQueryDto> orderItems;

  public OrderQueryDto(Long orderId, String name, LocalDateTime orderDate,
      OrderStatus orderStatus, Address address) {

    this.orderId = orderId;
    this.name = name;
    this.orderDate = orderDate;
    this.orderStatus = orderStatus;
    this.address = address;
  }

  public OrderQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address, List<OrderItemQueryDto> orderItems) {
    this.orderId = orderId;
    this.name = name;
    this.orderDate = orderDate;
    this.orderStatus = orderStatus;
    this.address = address;
    this.orderItems = orderItems;
  }

}
