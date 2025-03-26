package com.sportscenter.domain.repository;

import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

public interface OrderRepository {
    List<Order> findByUserId(int userId);
    
}
