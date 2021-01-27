/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-27 23:16:51
 * @modify date 2021-01-27 23:16:51
 * @desc [description]
 */
package com.cg.paymentservice.controller;

import javax.servlet.http.HttpServletRequest;

import com.cg.paymentservice.entities.PaymentEntry;
import com.cg.paymentservice.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/payment")
public class PaymentController {

  @Autowired
  private PaymentService paymentService;

  @PostMapping("/charge")
  public PaymentEntry chargeCard(HttpServletRequest request) throws Exception {
    String token = request.getHeader("token");
    Double amount = Double.parseDouble(request.getHeader("amount"));
    return paymentService.chargeCreditCard(token, amount);
  }
}
