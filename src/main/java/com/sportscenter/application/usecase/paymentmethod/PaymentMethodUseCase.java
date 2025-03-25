package com.sportscenter.application.usecase.paymentmethod;

import com.sportscenter.domain.entities.PaymentMethod;
import com.sportscenter.domain.repository.PaymentMethodRepository;
import java.util.List;

public class PaymentMethodUseCase {
    private final PaymentMethodRepository repository;

    public PaymentMethodUseCase(PaymentMethodRepository repository) {
        this.repository = repository;
    }

    public void registerPaymentMethod(String methodName, String description) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setMethodName(methodName);
        paymentMethod.setDescription(description);
        repository.save(paymentMethod);
    }

    public PaymentMethod getPaymentMethodById(int id) {
        return repository.findById(id);
    }

    public List<PaymentMethod> getAllPaymentMethods() {
        return repository.findAll();
    }

    public void updatePaymentMethod(int id, String methodName, String description) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(id);
        paymentMethod.setMethodName(methodName);
        paymentMethod.setDescription(description);
        repository.update(paymentMethod);
    }

    public void deletePaymentMethod(int id) {
        repository.delete(id);
    }
}