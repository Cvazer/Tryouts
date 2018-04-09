package com.github.cvazer.tryout.playgendary.services;

import com.github.cvazer.tryout.playgendary.model.Employee;

import java.util.List;

public interface EmployeeService extends Service{
    void save(Employee employee);
    void delete(Long id);
    void edit(Employee employee, Long id);
    Employee get(Long id);
    List<Employee> getAll();
}
