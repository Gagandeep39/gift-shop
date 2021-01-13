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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/orders")
@RestController
public class OrderController {

  @GetMapping("/user/{id}")
  List<OrderMain> fetchByUserId(@PathVariable Long userId) {
    return null;
  }

  @GetMapping
  List<OrderMain> fetchAll() {
    return null;
  }

  @GetMapping("/{id}")
  OrderMain findByOrderId(Long orderId) {
    return null;
  }

  @PostMapping
  OrderMain updateStatus(UpdateStatusDto updateStatusDto) {
    return null;
  }

}
