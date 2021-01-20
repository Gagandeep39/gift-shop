/**
 * @author Sonali Deshmukh
 * @email sonali1998deshmukh@gmail.com
 * @create date 2021-01-14 01:12:12
 * @modify date 2021-01-14 01:12:12
 * @desc [description]
 */
package com.cg.orderservice.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.orderservice.dto.UpdateStatusDto;
import com.cg.orderservice.entities.OrderMain;
import com.cg.orderservice.exception.CustomException;
import com.cg.orderservice.repositories.OrderMainRepository;
import com.cg.orderservice.services.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
  
	@Autowired
	OrderMainRepository ordermainrepository;


  @Override
  public List<OrderMain> fetchByUserId(Long userId) {
    return ordermainrepository.findByUserId(userId);
  }

  @Override
  public List<OrderMain> fetchAll() {
    return ordermainrepository.findAll();
  }

  @Override
  public OrderMain findByOrderId(Long orderId) {
    return ordermainrepository.findById(orderId).orElseThrow(() -> new CustomException("order", "Invalid Order ID"));
  }

  @Override
  public OrderMain updateOrderStatus(UpdateStatusDto updateStatusDto) {
    return null;
  }

}
