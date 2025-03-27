package com.sportscenter.domain.repository;

import java.util.List;

import com.sportscenter.domain.entities.Invoice;

//FACTURA
public interface InvoiceRepository {
    void save(Invoice invoice);
    Invoice searchById(int id);
    List<Invoice> listAll();
    List<Invoice> findByUserId(int userId); 
    void update(Invoice invoice);
    void delete(int id);
}
