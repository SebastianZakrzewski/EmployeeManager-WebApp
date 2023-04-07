package com.example.webappbackend.service;
import com.example.webappbackend.dao.EmployeeDao;
import com.example.webappbackend.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class EmployeeServiceTest {
    @Mock
    private EmployeeDao dao;
    @InjectMocks
    private EmployeeService service;

    Employee employee;
    @BeforeEach
    public void prepared() {
        employee = Employee.builder().id(1L).name("Thomas").build();
    }
    @Test
    void shouldGetById() {
        given(dao.read(1L)).willReturn(employee);
        Employee returned = service.getEmployeeById(employee.getId());
        Assertions.assertAll(
                () -> Assertions.assertNotNull(returned, () -> "Entity is null"),
                () -> Assertions.assertEquals("Thomas",returned.getName(),  () -> "Value is different")
        );
    }
    @Test
    void shouldCreateEmployee() {
        given(dao.create(employee)).willReturn(employee);
        Employee returned = service.createEmployee(employee);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(returned, () -> "Entity is null"),
                () -> Assertions.assertEquals("Thomas",returned.getName(),  () -> "Value is different")
        );
    }
    @Test
    void shouldReturnEmployeeList() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        given(dao.getEmployeeList()).willReturn(employeeList);
        List<Employee> returned = service.getListOfEmployee();
        Assertions.assertAll(
                () -> Assertions.assertNotNull(returned.get(0), () -> "Value doesn't exist"),
                () -> Assertions.assertEquals("Thomas",returned.get(0).getName(),  () -> "Value is different"),
                () -> Assertions.assertEquals(1,employeeList.size())
                );
    }

    @Test
    void updateEmployee() {

        Employee employee = Employee.builder().name("Thomas").build();
        Employee employeeAfterUpdate = Employee.builder().name("Patric").build();
        given(dao.readByEmployeeCode(anyInt())).willReturn(employee);
        given(dao.update(employee)).willReturn(employeeAfterUpdate);
        Employee returned = service.updateEmployee(anyLong(),employeeAfterUpdate);

        System.out.println(employee.getName());
        System.out.println(returned.getName());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(returned, () -> "Value doesn't exist"),
                () -> Assertions.assertEquals(employee.getName(),returned.getName())
        );

    }




}