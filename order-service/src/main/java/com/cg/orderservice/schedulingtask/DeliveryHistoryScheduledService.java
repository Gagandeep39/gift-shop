/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-20 19:28:18
 * @modify date 2021-01-20 19:28:18
 * @desc [description]
 */
package com.cg.orderservice.schedulingtask;

import java.util.Date;
import java.util.List;

import com.cg.orderservice.entities.DeliveryHistory;
import com.cg.orderservice.entities.OrderMain;
import com.cg.orderservice.enums.OrderStatus;
import com.cg.orderservice.repositories.DeliveryHistoryRepository;
import com.cg.orderservice.repositories.OrderMainRepository;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class DeliveryHistoryScheduledService {

  private final DeliveryHistoryRepository deliveryRepository;
  private final OrderMainRepository orderRepository;

  @Scheduled(cron = "5 * * * * *")
  public void autoApproveLeave() {
    log.info("Executed at " + new Date());
    List<OrderMain> orders = orderRepository.fetchOrderBasedOnStatus();
    // Update order status
    for (OrderMain orderMain : orders) {
      if (orderMain.getOrderStatus().equals(OrderStatus.NEW))
        orderMain.setOrderStatus(OrderStatus.DISPATCHED);
      else if (orderMain.getOrderStatus().equals(OrderStatus.DISPATCHED))
        orderMain.setOrderStatus(OrderStatus.OUT_FOR_DELIVERY);
      else if (orderMain.getOrderStatus().equals(OrderStatus.OUT_FOR_DELIVERY))
        orderMain.setOrderStatus(OrderStatus.DELIVERED);
      orderRepository.save(orderMain);
      // Add to delivery table
      DeliveryHistory history = new DeliveryHistory();
      history.setOrderStatus(orderMain.getOrderStatus());
      history.setUpdatedOn(System.currentTimeMillis());
      history.setOrderId(orderMain.getOrderId());
      deliveryRepository.save(history);
    }

  }
}
