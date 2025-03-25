package com.sportscenter.application.usecase.orderstatus;

import com.sportscenter.domain.entities.OrderStatus;
import com.sportscenter.domain.repository.OrderStatusRepository;
import java.util.List;

public class OrderStatusUseCase {
    private final OrderStatusRepository repository;

    public OrderStatusUseCase(OrderStatusRepository repository) {
        this.repository = repository;
    }

    public void registerOrderStatus(String statusName, String description) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setStatusName(statusName);
        orderStatus.setDescription(description);
        repository.save(orderStatus);
    }

    public OrderStatus getOrderStatusById(int id) {
        return repository.findById(id);
    }

    public List<OrderStatus> getAllOrderStatuses() {
        return repository.findAll();
    }

    public void updateOrderStatus(int id, String statusName, String description) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(id);
        orderStatus.setStatusName(statusName);
        orderStatus.setDescription(description);
        repository.update(orderStatus);
    }

    public void deleteOrderStatus(int id) {
        repository.delete(id);
    }
}