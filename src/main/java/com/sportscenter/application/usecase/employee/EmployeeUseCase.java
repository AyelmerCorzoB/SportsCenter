package com.sportscenter.application.usecase.employee;

import com.sportscenter.domain.entities.Employee;
import com.sportscenter.domain.repository.EmployeeRepository;
import java.util.List;

public class EmployeeUseCase {
    private final EmployeeRepository repository;

    public EmployeeUseCase(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void registerEmployee(String name, String position, String phone,
                               String email, int userId) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setPosition(position);
        employee.setPhone(phone);
        employee.setEmail(email);
        employee.setUserId(userId);
        repository.save(employee);
    }

    public Employee getEmployeeById(int id) {
        return repository.searchById(id);
    }

    public List<Employee> getAllEmployees() {
        return repository.listAll();
    }

    public void updateEmployee(int id, String name, String position, String phone,
                             String email, int userId) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setPosition(position);
        employee.setPhone(phone);
        employee.setEmail(email);
        employee.setUserId(userId);
        repository.update(employee);
    }

    public void deleteEmployee(int id) {
        repository.delete(id);
    }
}