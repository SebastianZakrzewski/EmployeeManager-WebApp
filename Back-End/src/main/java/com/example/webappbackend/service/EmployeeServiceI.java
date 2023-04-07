package com.example.webappbackend.service;
import com.example.webappbackend.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeServiceI {
    Employee getEmployeeById(Long id);
    Employee createEmployee(Employee employee);
    List<Employee> getListOfEmployee();
    Employee updateEmployee(Long id, Employee employee);
    boolean deleteEmployee(Long id);

}

