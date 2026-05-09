package com.nhatsinh.apartmentmanagement.service;

import org.springframework.stereotype.Component;

import com.nhatsinh.apartmentmanagement.dto.PaymentResponse;
import com.nhatsinh.apartmentmanagement.entity.Payment;

@Component
public class PaymentMapper {
  public PaymentResponse toResponse(Payment payment){
    if(payment == null) return null;

    return new PaymentResponse(
        payment.getId(),
        payment.getAmount(),
        payment.getMethod(),
        payment.getPaymentDate(),
        payment.getInvoice().getId()
    );
  }
}
