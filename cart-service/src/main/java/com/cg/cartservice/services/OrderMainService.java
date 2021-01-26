package com.cg.cartservice.services;

import java.util.Map;

import javax.validation.Valid;

import com.cg.cartservice.dto.OrderDto;

public interface OrderMainService {

  Map<String, String> checkOut(Long id);

  Map<String, String> manualCheckout(Long id, OrderDto orderDto);

}
