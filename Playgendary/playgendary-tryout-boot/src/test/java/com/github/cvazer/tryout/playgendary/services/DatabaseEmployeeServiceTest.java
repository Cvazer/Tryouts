package com.github.cvazer.tryout.playgendary.services;

import com.github.cvazer.tryout.playgendary.exceptions.NoEntityException;
import com.github.cvazer.tryout.playgendary.model.Employee;
import com.github.cvazer.tryout.playgendary.repositories.EmployeeRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@ContextConfiguration
public class DatabaseEmployeeServiceTest {

    @TestConfiguration()
    static class Config{
        public @Bean EmployeeRepo repo(){
            return mock(EmployeeRepo.class);
        }
        public @Bean EmployeeService service(){
            return new DatabaseEmployeeService(repo());
        }
    }

    private @Autowired EmployeeRepo repo;
    private @Autowired EmployeeService service;

    @Test
    public void saveEmployee(){
        Employee employee = mock(Employee.class);
        when(repo.save(any(Employee.class))).thenReturn(null);
        service.save(employee);
        verify(repo, times(1)).save(employee);
    }

    @Test
    public void deleteEmployee(){
        when(repo.existsById(anyLong())).thenReturn(true);
        doNothing().when(repo).deleteById(anyLong());
        service.delete(1L);
        verify(repo, times(1)).deleteById(1L);
    }

    @Test(expected = NoEntityException.class)
    public void deleteEmployeeFail(){
        when(repo.existsById(anyLong())).thenReturn(false);
        doNothing().when(repo).deleteById(anyLong());
        service.delete(anyLong());
        service.delete(anyLong());
    }

    @Test
    public void editEmployee(){
        Employee employee = new Employee("A", "B", "C");
        Employee employee2 = new Employee("D", "E", "F");
        when(repo.existsById(anyLong())).thenReturn(true);
        when(repo.getOne(anyLong())).thenReturn(employee);
        service.edit(employee2, anyLong());
        assertEquals("DEF", employee.getFirstName()+employee.getLastName()+employee.getSurname());
    }

    @Test
    public void getAllEmployees(){
        Employee employee = mock(Employee.class);
        when(repo.findAll()).thenReturn(Collections.singletonList(employee));
        assertTrue(service.getAll().stream().findFirst().isPresent());
        assertSame(employee, service.getAll().stream().findFirst().get());
    }

    @Test
    public void geRoom(){
        Employee employee = mock(Employee.class);
        when(repo.findById(anyLong())).then(invocation -> Optional.of(employee));
        when(repo.existsById(anyLong())).thenReturn(true);
        assertSame(employee, service.get(anyLong()));
    }

}
