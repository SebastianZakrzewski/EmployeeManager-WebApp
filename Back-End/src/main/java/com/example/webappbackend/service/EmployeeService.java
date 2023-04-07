package com.example.webappbackend.service;
import com.example.webappbackend.dao.CrudDao;
import com.example.webappbackend.model.Employee;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@Transactional
public class EmployeeService implements EmployeeServiceI {
    private final CrudDao<Employee> dao;
    public EmployeeService(CrudDao<Employee> dao) {
        this.dao = dao;
    }
    @Override
    public Employee getEmployeeById(Long id) throws EntityNotFoundException {
        Employee employee = dao.read(id);
        if (employee == null) {
            throw new EntityNotFoundException("Entity doesn't exist under this id");
        }
        return dao.read(id);
    }

    @Override
    public Employee createEmployee(Employee employee) {
       return dao.create(employee);
    }
    @Override
    public List<Employee> getListOfEmployee() {
        List<Employee> employeeList = dao.getEmployeeList();
        if (employeeList.isEmpty()) {
            throw new EntityNotFoundException("Collection of entity doesn't exist under this endpoint");
        }
       return this.dao.getEmployeeList();
    }

    @Override
    @Transactional
    public Employee updateEmployee(Long id, Employee employee) {

        var employeeUpdate = dao.readByEmployeeCode(Math.toIntExact(id));

        if (checkNullOrNot(employee.getName())) {
            employeeUpdate.setName(employee.getName());
        }
        if (checkNullOrNot(employee.getEmail())) {
            employeeUpdate.setEmail(employee.getEmail());
        }
        if (checkNullOrNot(employee.getTittle())) {
            employeeUpdate.setTittle(employee.getTittle());
        }
        if (checkNullOrNot(employee.getPhone())) {
            employeeUpdate.setPhone(employee.getPhone());
        }

        if (checkNullOrNot(employee.getFile())) {
            employeeUpdate.setFile(employee.getFile());
        }

        return dao.update(employeeUpdate);
    }

    private boolean checkNullOrNot(String value) {
        return value != null && value != "";
    }

    @Override
    public boolean deleteEmployee(Long id) {
        dao.deleteById(id);
        return true;
    }


}
