package com.cg.paymentservice.service;

import com.cg.paymentservice.dto.RazorDto;
import com.cg.paymentservice.entities.PaymentEntry;

public interface PaymentService {

  PaymentEntry chargeCreditCard(String token, double amount) throws Exception;

  PaymentEntry saveRazorPayment(RazorDto dto);
  
}
