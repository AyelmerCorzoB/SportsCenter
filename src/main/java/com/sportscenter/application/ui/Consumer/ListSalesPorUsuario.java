package com.sportscenter.application.ui.Consumer;

import com.sportscenter.domain.entities.Sale;
import com.sportscenter.domain.entities.SaleDetail;
import com.sportscenter.domain.repository.SaleDetailRepository;
import com.sportscenter.domain.repository.SaleRepository;

import java.util.List;

public class ListSalesPorUsuario {

    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;

    public ListSalesPorUsuario(SaleRepository saleRepository, SaleDetailRepository saleDetailRepository) {
        this.saleRepository = saleRepository;
        this.saleDetailRepository = saleDetailRepository;
    }

    public void mostrarPorUsuario(int userId) {
        List<Sale> sales = saleRepository.findByUserId(userId); 
        
        if (sales == null || sales.isEmpty()) {
            System.out.println("No se encontraron compras para este usuario.");
            return;
        }

        System.out.println("\nCompras del usuario:");
        for (Sale sale : sales) {
            System.out.println("\nFecha: " + sale.getSaleDate());
            System.out.println("$ Total: " + sale.getTotal());
            System.out.println("Detalles de la venta:");
            
            List<SaleDetail> saleDetails = saleDetailRepository.findBySaleId(sale.getId());
            
            if (saleDetails == null || saleDetails.isEmpty()) {
                System.out.println("  No hay detalles disponibles para esta venta.");
            } else {
                for (SaleDetail detail : saleDetails) {
                    System.out.println("| Cantidad: " + detail.getQuantity() + 
                                     "- Precio unitario: " + detail.getUnitPrice() + 
                                     "- Subtotal: " + detail.getSubtotal());
                }
            }
            System.out.println("-------------------------");
        }
    }
}