package com.example.webappbackend.restAPI;
import com.example.webappbackend.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.webappbackend.service.EmployeeServiceI;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin()
public class EmployeeController {
    private final EmployeeServiceI service;
    private final ObjectMapper mapper;
    public EmployeeController(EmployeeServiceI service, ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
    @RequestMapping(value = "employee/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUserById(@PathVariable("id") Long id) {
        service.deleteEmployee(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @RequestMapping(value = "/employee/update/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Employee> updateUser(@PathVariable("id") Long id, @RequestBody Employee employee) throws JsonProcessingException {
        var returned = service.updateEmployee(id,employee);
        return new ResponseEntity<>(returned,HttpStatus.OK);
    }
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getEmployeeById(id),HttpStatus.OK);
    }
    @RequestMapping(value = "/employee/create", method = RequestMethod.POST)
    public ResponseEntity<Employee> createNewEmployee(@RequestBody Employee employee) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("location","/create");
        employee = service.createEmployee(employee);
        return new ResponseEntity(employee,httpHeaders,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/employee/upload", method = RequestMethod.POST)
    public String createNewEmployee(MultipartFile file) throws IOException {
        var httpHeaders = new HttpHeaders();
        byte b[] = file.getBytes();
        httpHeaders.add("location","/create");
        return "success";
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getUsersList () {
        return new ResponseEntity<>(service.getListOfEmployee(),HttpStatus.OK);
    }

}
