/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-14 01:12:12
 * @modify date 2021-01-14 01:12:12
 * @desc [description]
 */
package com.cg.orderservice.services.implementation;

import java.util.List;

import com.cg.orderservice.dto.UpdateStatusDto;
import com.cg.orderservice.entities.OrderMain;
import com.cg.orderservice.services.OrderService;

public class OrderServiceImpl implements OrderService {

  @Override
  public List<OrderMain> fetchByUserId(Long userId) {
    // Auto-generated method stub
    return null;
  }

  @Override
  public List<OrderMain> fetchAll() {
    // Auto-generated method stub
    return null;
  }

  @Override
  public OrderMain findByOrderId(Long orderId) {
    // Auto-generated method stub
    return null;
  }

  @Override
  public OrderMain updateStatus(UpdateStatusDto updateStatusDto) {
    // Auto-generated method stub
    return null;
  }
  
}
