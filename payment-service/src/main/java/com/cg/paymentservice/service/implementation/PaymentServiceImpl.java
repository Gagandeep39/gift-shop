package com.cg.paymentservice.service.implementation;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.cg.paymentservice.dto.RazorDto;
import com.cg.paymentservice.entities.PaymentEntry;
import com.cg.paymentservice.repositories.PaymentEntryRepository;
import com.cg.paymentservice.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

  @Autowired
  private PaymentEntryRepository paymentRepository;

  @Value("${stripe.key}")
  private String key;

  @Override
  public PaymentEntry chargeCreditCard(String token, double amount) throws StripeException {
    Stripe.apiKey = key;
    Map<String, Object> chargeParams = new HashMap<String, Object>();
    chargeParams.put("amount", (int) (amount * 100));
    chargeParams.put("currency", "INR");
    chargeParams.put("source", token);
    Charge charge = Charge.create(chargeParams);
    PaymentEntry entry = new PaymentEntry();
    entry.setAmount(BigDecimal.valueOf(charge.getAmount()/100));
    entry.setName(charge.getBillingDetails().getName());
    entry.setGeneratedId(charge.getId());
    paymentRepository.save(entry);
    return entry;
  }

  @Override
  public PaymentEntry saveRazorPayment(RazorDto dto) {
    PaymentEntry entry = new PaymentEntry();
    entry.setAmount(BigDecimal.valueOf(dto.getAmount()));
    entry.setName(dto.getName());
    entry.setGeneratedId(dto.getRazorId());
    return paymentRepository.save(entry);
  }
}
