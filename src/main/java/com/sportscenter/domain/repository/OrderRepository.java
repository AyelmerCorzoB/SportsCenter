package com.sportscenter.domain.repository;

import com.sportscenter.domain.entities.Order;
import java.util.List;

public interface OrderRepository {
    List<Order> findByUserId(int userId);
}

