package com.skeletonhexa.application.usecase.invoice;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.skeletonhexa.domain.entities.Invoice;
import com.skeletonhexa.domain.repository.InvoiceRepository;

public class InvoiceUseCase {
    private final InvoiceRepository repository;

    public InvoiceUseCase(InvoiceRepository repository){
        this.repository = repository;
    }

    public void registerInvoice(int saleId, String invoiceNumber, Date issueDate, BigDecimal totalAmount, BigDecimal taxes){
        Invoice invoice = new Invoice(saleId, invoiceNumber, issueDate, totalAmount, taxes);
        repository.save(invoice);
    }

    public Invoice getInvoice(int id){
        return repository.searchById(id);
    }

    public List<Invoice> ListInvoices(){
        return repository.listAll();
    }

    public void updateCategry(int id, int saleId, String invoiceNumber, Date issueDate, BigDecimal totalAmount, BigDecimal taxes){
        Invoice invoice = new Invoice(id, saleId, invoiceNumber, issueDate, totalAmount, taxes);
        repository.update(invoice);
    }

    public void deleteInvoice(int id){
        repository.delete(id);
    }
}