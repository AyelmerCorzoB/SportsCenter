package com.skeletonhexa.domain.repository;

import java.util.List;

import com.skeletonhexa.domain.entities.Employee;

public interface EmployeeRepository {
    void save(Employee emploEmployee);
    Employee searchById(int id);
    List<Employee> listAll();
    void update(Employee emploEmployee);
    void delete(int id);
}
