package com.sportscenter.application.ui.Consumer;

import com.sportscenter.domain.entities.Order;
import com.sportscenter.domain.repository.OrderRepository;

import java.util.List;

public class ListarOrdersPorUsuario {

    private final OrderRepository orderRepository;

    public ListarOrdersPorUsuario(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void mostrarPorUsuario(int userId) {
        List<Order> orders = orderRepository.findByUserId(userId);

        if (orders == null || orders.isEmpty()) {
            System.out.println("No se encontraron órdenes para este usuario.");
            return;
        }

        System.out.println("\n📦 Órdenes del usuario:");
        for (Order order : orders) {
            System.out.println("🆔 ID: " + order.getId());
            System.out.println("📅 Fecha: " + order.getOrderDate());
            System.out.println("📌 Estado: " + order.getStatus());
            System.out.println("-------------------------");
        }
    }
}
