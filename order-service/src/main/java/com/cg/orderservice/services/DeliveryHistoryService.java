package com.cg.orderservice.services;

import java.util.List;

import com.cg.orderservice.entities.DeliveryHistory;

public interface DeliveryHistoryService {

  List<DeliveryHistory> fetchDeliveriesByOrderId(Long orderId);
  
}
