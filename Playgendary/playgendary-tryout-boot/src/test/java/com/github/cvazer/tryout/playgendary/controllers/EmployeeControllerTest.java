package com.github.cvazer.tryout.playgendary.controllers;

import com.github.cvazer.tryout.playgendary.model.Employee;
import com.github.cvazer.tryout.playgendary.services.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService service;

    @Test
    public void getAll() throws Exception {
        Employee employee = new Employee("Yan", "F", "K");
        employee.setId(1L);
        given(service.getAll())
                .willReturn(Collections.singletonList(employee));
        mvc.perform(get("/employee/getAll")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"firstName\":\"Yan\",\"lastName\":\"F\",\"surname\":\"K\"}]"));
    }

    @Test
    public void getById() throws Exception {
        Employee employee = new Employee("Julia", "R", "A");
        employee.setId(2L);
        given(service.get(2L))
                .willReturn(employee);
        mvc.perform(get("/employee/get/2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":2,\"firstName\":\"Julia\",\"lastName\":\"R\",\"surname\":\"A\"}"));
    }

    @Test
    public void save() throws Exception {
        mvc.perform(put("/employee/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"John\",\"lastName\":\"D\",\"surname\":\"U\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void edit() throws Exception {
        mvc.perform(patch("/employee/edit/4")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Bill\",\"lastName\":\"K\",\"surname\":\"U\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteById() throws Exception {
        mvc.perform(delete("/employee/delete/5"))
                .andExpect(status().isOk());
    }
}
