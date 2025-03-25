package com.sportscenter.domain.repository;

import java.util.List;
import com.sportscenter.domain.entities.OrderStatus;

public interface OrderStatusRepository {
    void save(OrderStatus orderStatus);
    OrderStatus findById(int id);
    List<OrderStatus> findAll();
    void update(OrderStatus orderStatus);
    void delete(int id);
}