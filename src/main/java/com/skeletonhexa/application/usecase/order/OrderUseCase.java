package com.skeletonhexa.application.usecase.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.skeletonhexa.domain.entities.Order;
import com.skeletonhexa.domain.repository.OrderRepository;

public class OrderUseCase {
    private final OrderRepository repository;

    public OrderUseCase(OrderRepository repository){
        this.repository = repository;
    }

    public void registerOrder(int customerId, Date orderDate, String status, BigDecimal total, int userId){
        Order Order = new Order(customerId, orderDate, status, total, userId);
        repository.save(Order);
    }

    public Order getOrder(int id){
        return repository.searchById(id);
    }

    public List<Order> ListOrders(){
        return repository.listAll();
    }

    public void updateOrder(int id, int customerId, Date orderDate, String status, BigDecimal total, int userId){
        Order Order = new Order(id, customerId, orderDate, status, total, userId);
        repository.update(Order);
    }

    public void deleteOrder(int id){
        repository.delete(id);
    }
}