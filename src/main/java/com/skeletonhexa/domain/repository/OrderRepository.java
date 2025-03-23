package com.skeletonhexa.domain.repository;

import java.util.List;

import com.skeletonhexa.domain.entities.Order;

public interface OrderRepository {
    void save(Order OrderOrder);
    Order searchById(int id);
    List<Order> listAll();
    void update(Order OrderOrder);
    void delete(int id);
}
