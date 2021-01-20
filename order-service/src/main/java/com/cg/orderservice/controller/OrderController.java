/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-14 00:59:19
 * @modify date 2021-01-14 00:59:19
 * @desc [description]
 */
package com.cg.orderservice.controller;

import java.util.List;

import com.cg.orderservice.dto.UpdateStatusDto;
import com.cg.orderservice.entities.OrderMain;
import com.cg.orderservice.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/orders")
@RestController
public class OrderController {
  @Autowired
  OrderService service;

  @GetMapping("/user/{id}")
  List<OrderMain> fetchByUserId(Long userId) {
    return service.fetchByUserId(userId);
  }

  @GetMapping("/all")
  List<OrderMain> fetchAll() {
    List<OrderMain> orderlist = service.fetchAll();
    return service.fetchAll();
  }

  @GetMapping("/{id}")
  OrderMain findByOrderId(Long orderId) {
    return service.findByOrderId(orderId);
  }

  @PostMapping("/status")
  OrderMain OrderStatus(UpdateStatusDto updateStatusDto) {
    return service.OrderStatus(updateStatusDto);
  }

  @PostMapping("/addorder")
  OrderMain addOrderMain(OrderMain order) {
    return service.addOrderMain(order);
  }

  @DeleteMapping("/deleteorder/{id}")
  OrderMain deleteByOrderId(UpdateStatusDto updateStatusDto) {
    return service.deleteByOrderId(updateStatusDto);
  }
}
