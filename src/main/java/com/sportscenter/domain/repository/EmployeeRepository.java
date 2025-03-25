package com.sportscenter.domain.repository;

import java.util.List;

import com.sportscenter.domain.entities.Employee;

public interface EmployeeRepository {
    void save(Employee emploEmployee);
    Employee searchById(int id);
    List<Employee> listAll();
    void update(Employee emploEmployee);
    void delete(int id);
}
