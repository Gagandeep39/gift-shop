package com.cg.orderservice.services.implementation;

import java.util.List;

import com.cg.orderservice.entities.DeliveryHistory;
import com.cg.orderservice.exception.CustomException;
import com.cg.orderservice.repositories.DeliveryHistoryRepository;
import com.cg.orderservice.services.DeliveryHistoryService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeliveryHistoryServiceImpl implements DeliveryHistoryService {

  private final DeliveryHistoryRepository deliveryRepository;

  @Override
  public List<DeliveryHistory> fetchDeliveriesByOrderId(Long orderId) {
    List<DeliveryHistory> history = deliveryRepository.findByOrderId(orderId);
    if (history.size() == 0)
      throw new CustomException("delivery", "Order delivery history doesn't exist");
    else
      return history;
  }

}
