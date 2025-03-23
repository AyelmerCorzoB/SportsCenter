package com.skeletonhexa.application.usecase.employee;

import java.util.List;

import com.skeletonhexa.domain.entities.Employee;
import com.skeletonhexa.domain.repository.EmployeeRepository;

public class EmployeeUseCase {
    private final EmployeeRepository repository;

    public EmployeeUseCase(EmployeeRepository repository){
        this.repository = repository;
    }

    public void registerEmployee(String name, String position, String phone, String email){
        Employee employee = new Employee(name, position, phone, email);
        repository.save(employee);
    }

    public Employee getEmployee(int id){
        return repository.searchById(id);
    }

    public List<Employee> ListEmployees(){
        return repository.listAll();
    }

    public void updateCategry(int id, String name, String position, String phone, String email){
        Employee employee = new Employee(id, name, position, phone,email);
        repository.update(employee);
    }

    public void deleteEmployee(int id){
        repository.delete(id);
    }
}