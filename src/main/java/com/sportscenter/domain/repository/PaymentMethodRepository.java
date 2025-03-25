package com.sportscenter.domain.repository;

import java.util.List;
import com.sportscenter.domain.entities.PaymentMethod;

public interface PaymentMethodRepository {
    void save(PaymentMethod paymentMethod);
    PaymentMethod findById(int id);
    List<PaymentMethod> findAll();
    void update(PaymentMethod paymentMethod);
    void delete(int id);
}