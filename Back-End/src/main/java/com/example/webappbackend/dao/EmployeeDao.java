package com.example.webappbackend.dao;
import com.example.webappbackend.model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class EmployeeDao implements CrudDao<Employee> {
    private int counter = 0;
    private final EntityManagerFactory factory;
    private EmployeeDao(EntityManagerFactory factory) {
        this.factory = factory;
    }
    @Override
    public Employee create(Employee value) {
        var manager = getManager();
        manager.getTransaction().begin();
        try {
            value.setCode(counter);
            manager.persist(value);
            counter++;
            return value;
        } finally {
            manager.getTransaction().commit();
            manager.close();
        }
    }


    @Override
    public Employee read(long id) {
        var manager = getManager();
        Employee employee;
        try {
        employee = manager.find(Employee.class,id);
        } finally {
            manager.close();}
        return employee;
    }


    @Override
    public Employee readByEmployeeCode(int id) {
        var manager = getManager();
        Employee employee;
        manager.getTransaction().begin();
        try {
            String hql = "FROM Employee M where M.code = "+id;
            employee = manager.createQuery(hql,Employee.class).getSingleResult();
        } finally {
            manager.close();

        }
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        var manager = getManager();
        manager.getTransaction().begin();
        try {
            manager.merge(employee);
        } finally {
             manager.getTransaction().commit();
             manager.close();
        }
        return employee;
    }

    @Override
    public boolean deleteById(Long id) {
        var manager = getManager();
        manager.getTransaction().begin();
        try {
            String hql = "FROM Employee M where M.code = "+id;
            Employee employee = manager.createQuery(hql,Employee.class).getSingleResult();
            manager.remove(employee);
            return true;
        } finally {
            manager.getTransaction().commit();
            manager.close();
        }
    }
    @Override
    public List<Employee> getEmployeeList() {
        var manager = getManager();
        List<Employee> list;
        manager.getTransaction().begin();
        try {
            list = manager.createQuery("FROM Employee").getResultList();
        } finally {
            manager.close();
        }
        return list;
    }
    private EntityManager getManager() {
        return factory.createEntityManager();
    }
}
