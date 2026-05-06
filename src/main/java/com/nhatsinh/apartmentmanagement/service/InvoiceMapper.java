package com.nhatsinh.apartmentmanagement.service;

import org.springframework.stereotype.Component;

import com.nhatsinh.apartmentmanagement.dto.InvoiceResponse;
import com.nhatsinh.apartmentmanagement.entity.Invoice;

@Component
public class InvoiceMapper {
    public InvoiceResponse toResponse(Invoice invoice) {
        if (invoice == null) return null;

        return new InvoiceResponse(
            invoice.getId(),
            invoice.getAmount(),
            invoice.getStatus(),
            invoice.getDueDate(),
            invoice.getResident().getId()
            
        );
    }

}
