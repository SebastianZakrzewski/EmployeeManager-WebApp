package com.example.webappbackend.DaoTest;
import com.example.webappbackend.dao.CrudDao;
import com.example.webappbackend.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class DaoTest {
    @Autowired
    private CrudDao<Employee> employeeCrudDao;
    private Employee employee;

    @BeforeEach
    public void createEntity() {
                employee = Employee.builder()
                .name("Thomas Wayne")
                .email("thomas@gmail.com")
                .tittle("Manager")
                .phone("512-152-612").build();
    }

    @Test
    public void shouldPersistEmployeeEntity() {
        Employee returned = employeeCrudDao.create(employee);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(returned),
                () -> Assertions.assertEquals("Thomas Wayne",returned.getName())
        );
    }

    @Test
    @Transactional
    public void shouldUpdateEmployeeEntity() {
        Employee beforeUpdate = employee;
        employeeCrudDao.create(employee);
        beforeUpdate.setName("Sebastian");
        employeeCrudDao.update(beforeUpdate);
        Employee afterUpdate = employeeCrudDao.read(employee.getId());
        Assertions.assertAll(
                () -> Assertions.assertNotNull(afterUpdate, () -> "Value is null"),
                () -> Assertions.assertNotEquals(beforeUpdate,afterUpdate.getName(), () -> "Update is failed")
        );
    }

    @Test
    @Transactional
    public void shouldReadEmployeeFromDB() {
        Employee returned = employeeCrudDao.create(employee);
        Employee read = employeeCrudDao.read(returned.getId());
        Assertions.assertAll(
                () -> Assertions.assertNotNull(read, () -> "Entity is null"),
                () -> Assertions.assertEquals("Thomas Wayne",read.getName(),  () -> "Value is different")
        );
    }

    @Test
    public void shouldDeleteEmployeeEntity() {
        Employee beforeDelete = employeeCrudDao.create(employee);
        boolean isDelete = employeeCrudDao.deleteById((long) beforeDelete.getCode());
        Assertions.assertAll(
                () -> Assertions.assertEquals(true,isDelete, () -> "Value must be true")
        );
    }

    @AfterEach
    void deleteEntityAfterEachTest () {

    }




}
