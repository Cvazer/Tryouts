package com.github.cvazer.tryout.playgendary.controllers;

import com.github.cvazer.tryout.playgendary.model.Employee;
import com.github.cvazer.tryout.playgendary.services.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Transactional
public class EmployeeController {

    private EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody Employee employee){
        service.save(employee);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void edit(@PathVariable Long id, @RequestBody Employee employee) throws RuntimeException{
        service.edit(employee, id);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAll(){
        return service.getAll();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) throws RuntimeException{
        service.delete(id);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getById(@PathVariable Long id){
        return service.get(id);
    }
}
