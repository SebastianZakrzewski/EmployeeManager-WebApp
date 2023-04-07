package com.example.webappbackend.ControllerTest;
import com.example.webappbackend.restAPI.EmployeeController;
import com.example.webappbackend.restAPI.ErrorHandling.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.webappbackend.model.Employee;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.example.webappbackend.service.EmployeeServiceI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(EmployeeController.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeServiceI employeeService;
    @Autowired
    private ObjectMapper mapper;
    @Captor
    private ArgumentCaptor<Employee> captor;
    @Captor
    private ArgumentCaptor<Long> longCapture = ArgumentCaptor.forClass(Long.class);
    private Employee employee;
    @BeforeEach
    public void preparedObject() {
        employee = Employee.builder().name("Alex").id(1L).build();
    }
    @Test
    void should_update_Employee() throws Exception {
        Map<String, String> employeeMap = new HashMap<>();
        employeeMap.put("name", "newName");
        mockMvc.perform(patch("/employee/update/" + employee.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(employeeMap)))
                .andExpect(status().isOk());
        verify(employeeService).updateEmployee(longCapture.capture(), captor.capture());
        Assertions.assertEquals(employeeMap.get("name"), captor.getValue().getName());
        Assertions.assertEquals(employee.getId(), longCapture.getValue());
    }
    @Test
    void should_GetData() throws Exception {
        String s = mapper.writeValueAsString(employee);
        given(employeeService.getEmployeeById(anyLong())).willReturn(employee);
           mockMvc.perform(get("/employee/1")
                        .content(mapper.writeValueAsString(employee)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alex"));
    }
    @Test
    void should_create_newEmployee() throws Exception {
        given(employeeService.createEmployee(any(Employee.class))).willReturn(employee);
        mockMvc.perform(post("/employee/create")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Alex"))
                .andExpect(header().exists("location"));
    }
    @Test
    void should_get_ListOfEmployees() throws Exception {
        List<Employee> list = List.of(new Employee());
        given(employeeService.getListOfEmployee()).willReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.
                        get("/employees").
                        accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    void should_deleteEmployee() throws Exception {
        mockMvc.perform(delete("/employee/delete/1")
                .accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isNoContent());
        verify(employeeService).deleteEmployee(longCapture.capture());
        Assertions.assertEquals(1L,longCapture.getValue());
    }

    @Test
    void shouldResponseErrorWhenThrowEntityNotFoundException() throws Exception {
        EntityNotFoundException entityNotFoundException = new EntityNotFoundException("Entity not found");
        when(employeeService.getEmployeeById(anyLong())).thenThrow(entityNotFoundException);
        mockMvc.perform(get("/employee/1000")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> Assertions.assertEquals(entityNotFoundException,result.getResolvedException()))
                .andExpect(result -> Assertions.assertEquals(entityNotFoundException.getMessage(),result.getResolvedException().getMessage()))
                .andExpect(status().isNotFound()).andReturn();
                 verify(employeeService).getEmployeeById(anyLong());
    }


}