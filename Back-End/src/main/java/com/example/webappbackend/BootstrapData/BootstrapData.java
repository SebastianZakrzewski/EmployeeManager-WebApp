package com.example.webappbackend.BootstrapData;
import com.example.webappbackend.model.Employee;
import com.example.webappbackend.service.EmployeeServiceI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner  {
    final EmployeeServiceI service;
    public BootstrapData(EmployeeServiceI service) {
        this.service = service;
    }
    @Override
    public void run(String... args) throws Exception {
        createData();
    }
    private void createData() {

        Employee employee = Employee.builder()
                .name("Sebastian Zakrzewski")
                .email("devzakrzewsi@gmail.com")
                .tittle("Full-Stack Dev")
                .phone("567-321-512")
                .build();

        Employee employee1 = Employee.builder()
                .name("Thomas Bergersen")
                .email("bergersen@gmail.com")
                .tittle("Manager")
                .phone("765-425-212")
                .build();

        Employee employee2 = Employee.builder()
                .name("Patric Wayne")
                .email("wayne@gmail.com")
                .tittle("UI/UX Designer")
                .phone("767-421-512")
                .build();

        Employee employee3 = Employee.builder()
                .name("Levis Amstrong")
                .email("amstrong@gmail.com")
                .tittle("Python Dev")
                .phone("685-126-231")
                .build();


        service.createEmployee(employee);
        service.createEmployee(employee1);
        service.createEmployee(employee2);
        service.createEmployee(employee3);


    }

}
