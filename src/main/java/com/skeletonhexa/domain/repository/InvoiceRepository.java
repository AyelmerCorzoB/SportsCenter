package com.skeletonhexa.domain.repository;

import java.util.List;

import com.skeletonhexa.domain.entities.Invoice;

//FACTURA
public interface InvoiceRepository {
void save(Invoice invoInvoice);
    Invoice searchById(int id);
    List<Invoice> listAll();
    void update(Invoice invoInvoice);
    void delete(int id);
}
