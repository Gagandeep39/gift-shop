package com.cg.cartservice.services;

import com.cg.cartservice.entities.OrderMain;

public interface OrderMainService {

  
  OrderMain checkOut(Long id);
  
}
