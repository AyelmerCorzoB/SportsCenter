package com.sportscenter.application.usecase.invoice;

import com.sportscenter.domain.entities.Invoice;
import com.sportscenter.domain.repository.InvoiceRepository;
import java.time.LocalDate;
import java.util.List;

public class InvoiceUseCase {
    private final InvoiceRepository repository;

    public InvoiceUseCase(InvoiceRepository repository) {
        this.repository = repository;
    }

    public void registerInvoice(int saleId, String invoiceNumber, LocalDate issueDate,
            double totalAmount, double taxes) {
        if (invoiceNumber == null || invoiceNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de factura no puede estar vacío");
        }
        if (issueDate == null) {
            throw new IllegalArgumentException("La fecha de emisión es requerida");
        }
        if (totalAmount <= 0) {
            throw new IllegalArgumentException("El monto total debe ser positivo");
        }
        if (taxes < 0) {
            throw new IllegalArgumentException("Los impuestos no pueden ser negativos");
        }

        Invoice invoice = new Invoice(saleId, invoiceNumber, issueDate);
        invoice.setTotalAmount(totalAmount);
        invoice.setTaxes(taxes);
        repository.save(invoice);
    }

    public Invoice getInvoiceById(int id) {
        return repository.searchById(id);
    }

    public List<Invoice> getAllInvoices() {
        return repository.listAll();
    }

    public List<Invoice> getInvoicesByUserId(int userId) {
        return repository.findByUserId(userId);
    }

    public void updateInvoice(Invoice invoice) {
        repository.update(invoice);
    }

    public void deleteInvoice(int id) {
        repository.delete(id);
    }
}