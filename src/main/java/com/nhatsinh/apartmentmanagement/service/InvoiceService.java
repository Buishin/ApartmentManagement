package com.nhatsinh.apartmentmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhatsinh.apartmentmanagement.dto.InvoiceRequest;
import com.nhatsinh.apartmentmanagement.dto.InvoiceResponse;
import com.nhatsinh.apartmentmanagement.dto.InvoiceUpdateRequest;
import com.nhatsinh.apartmentmanagement.entity.Invoice;
import com.nhatsinh.apartmentmanagement.entity.Resident;
import com.nhatsinh.apartmentmanagement.exception.ResourceNotFoundException;
import com.nhatsinh.apartmentmanagement.repository.InvoiceRepository;
import com.nhatsinh.apartmentmanagement.repository.ResidentRepository;

@Service
public class InvoiceService {
     private final InvoiceRepository invoiceRepository;
    private final ResidentRepository residentRepository;
    private final InvoiceMapper invoiceMapper;

    public InvoiceService(
            InvoiceRepository invoiceRepository,
            ResidentRepository residentRepository,
            InvoiceMapper invoiceMapper) {

        this.invoiceRepository = invoiceRepository;
        this.residentRepository = residentRepository;
        this.invoiceMapper = invoiceMapper;
    }

     @Transactional
    public InvoiceResponse create(InvoiceRequest request) {
        Resident resident = residentRepository.findById(request.resident_id())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resident", request.resident_id()));
        
        Invoice invoice = Invoice.builder()
                        .amount(request.amount())
                        .status(request.status())
                        .dueDate(request.dueDate())
                        .resident(resident)
                        .build();



        invoice = invoiceRepository.save(invoice);

        return invoiceMapper.toResponse(invoice);
    }

    @Transactional(readOnly = true)
        public Page<InvoiceResponse> findAll(Pageable pageable) {
            return invoiceRepository.findAll(pageable)
                    .map(invoiceMapper::toResponse);
        }
    
    @Transactional(readOnly = true)
        public InvoiceResponse findById(Long id) {
            Invoice invoice = invoiceRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Invoice", id));
    
            return invoiceMapper.toResponse(invoice);
        }
    
    @Transactional
    public InvoiceResponse update(Long id, InvoiceUpdateRequest request) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invoice", id));

                invoice.setAmount(request.amount());
                invoice.setDueDate(request.dueDate());
                invoice.setStatus(request.status());

                invoice = invoiceRepository.save(invoice);

                return invoiceMapper.toResponse(invoice);
            }
    @Transactional(readOnly=true)
    public Page<InvoiceResponse> findMyInvoices(String username, Pageable pageable){
        return invoiceRepository.findByResidentUserUsername(username, pageable)
        .map(invoiceMapper::toResponse);
    }
    @Transactional(readOnly=true)
    public InvoiceResponse findMyInvoiceById(long id,String username){
        Invoice invoice = invoiceRepository.findByIdAndResidentUserUsername(id, username)
        .orElseThrow(()-> new ResourceNotFoundException("Invoice",id));

        return invoiceMapper.toResponse(invoice);

    }
    

}
