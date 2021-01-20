/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-14 00:59:19
 * @modify date 2021-01-14 00:59:19
 * @desc [description]
 */
package com.cg.orderservice.controller;

import java.util.List;

import javax.validation.Valid;

import com.cg.orderservice.dto.UpdateStatusDto;
import com.cg.orderservice.entities.OrderMain;
import com.cg.orderservice.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/orders")
@RestController
@CrossOrigin
public class OrderController {
  @Autowired
  private OrderService service;

  @GetMapping("/user/{userId}")
  List<OrderMain> fetchByUserId(@PathVariable Long userId) {
    return service.fetchByUserId(userId);
  }

  @GetMapping
  List<OrderMain> fetchAll() {
    return service.fetchAll();
  }

  @GetMapping("/{id}")
  OrderMain findByOrderId(@PathVariable Long id) {
    return service.findByOrderId(id);
  }

  @PostMapping
  OrderMain updateOrderStatus(@Valid @RequestBody UpdateStatusDto updateStatusDto) {
    return service.updateOrderStatus(updateStatusDto);
  }

}
