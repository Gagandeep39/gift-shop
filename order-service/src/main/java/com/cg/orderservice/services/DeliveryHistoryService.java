/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-28 00:32:22
 * @modify date 2021-01-28 00:32:22
 * @desc [description]
 */
package com.cg.orderservice.services;

import java.util.List;

import com.cg.orderservice.entities.DeliveryHistory;
import com.cg.orderservice.enums.OrderStatus;

public interface DeliveryHistoryService {

  List<DeliveryHistory> fetchDeliveriesByOrderId(Long orderId);

  DeliveryHistory createEntry(Long orderId, OrderStatus status);
  
}
