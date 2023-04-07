package com.example.webappbackend.dao;
import com.example.webappbackend.model.Employee;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T> {
    Employee create(T value);
    Employee read(long id);
    Employee readByEmployeeCode(int id);
    Employee update(T t);
    boolean deleteById(Long id);
    List<T> getEmployeeList();

}

