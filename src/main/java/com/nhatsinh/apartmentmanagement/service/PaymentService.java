package com.nhatsinh.apartmentmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhatsinh.apartmentmanagement.dto.PaymentRequest;
import com.nhatsinh.apartmentmanagement.dto.PaymentResponse;
import com.nhatsinh.apartmentmanagement.entity.Invoice;
import com.nhatsinh.apartmentmanagement.entity.Payment;
import com.nhatsinh.apartmentmanagement.exception.ResourceNotFoundException;
import com.nhatsinh.apartmentmanagement.repository.InvoiceRepository;
import com.nhatsinh.apartmentmanagement.repository.PaymentRepository;

@Service
public class PaymentService {
    private  final PaymentRepository paymentRepository;
    private  final PaymentMapper paymentMapper;
    private  final InvoiceRepository invoiceRepository;

    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper, InvoiceRepository invoiceRepository){
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.invoiceRepository= invoiceRepository;
    }
    @Transactional
    public PaymentResponse create(PaymentRequest request){
        Invoice invoice = invoiceRepository.findById(request.invoice_id())
        .orElseThrow(() -> new ResourceNotFoundException("Invoice", request.invoice_id()));

        Payment payment = Payment.builder()
        .amount(request.amount())
        .method(request.method())
        .paymentDate(request.paymentDate())
        .invoice(invoice)
        .build();

        payment = paymentRepository.save(payment);

        return  paymentMapper.toResponse((payment));
    
    }

    @Transactional(readOnly=true)
    public  Page<PaymentResponse> findAll(Pageable pageable){
        return paymentRepository.findAll(pageable)
        .map(paymentMapper::toResponse);
    }  
    @Transactional(readOnly=true)
    public PaymentResponse findById(long id){
        Payment payment = paymentRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Payment",id));
            return paymentMapper.toResponse((payment)); 
        
    }
    @Transactional(readOnly = true)
    public Page<PaymentResponse> findMyPayments(
        String username,
        Pageable pageable) {

    return paymentRepository
            .findByInvoiceResidentUserUsername(username, pageable)
            .map(paymentMapper::toResponse);
     }    
    @Transactional(readOnly=true)
    public PaymentResponse findMyPaymentsById(long id, String username){
        Payment payment = paymentRepository
            .findByIdAndInvoiceResidentUserUsername(id, username)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Payment", id));

    return paymentMapper.toResponse(payment);
    }
}

