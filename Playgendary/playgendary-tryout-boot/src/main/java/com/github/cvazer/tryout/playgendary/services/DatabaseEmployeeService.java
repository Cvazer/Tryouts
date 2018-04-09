package com.github.cvazer.tryout.playgendary.services;

import com.github.cvazer.tryout.playgendary.exceptions.NoEntityException;
import com.github.cvazer.tryout.playgendary.model.Employee;
import com.github.cvazer.tryout.playgendary.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseEmployeeService implements EmployeeService{

    private EmployeeRepo repo;

    @Autowired
    public DatabaseEmployeeService(EmployeeRepo repo) {
        this.repo = repo;
    }

    @Override
    public void save(Employee employee) {
        repo.save(employee);
    }

    @Override
    public void delete(Long id) throws RuntimeException {
        exceptionIfNonExisting(id, ("No such employee by id "+id), repo);
        repo.deleteById(id);
    }

    @Override
    public void edit(Employee employee, Long id) throws RuntimeException{
        exceptionIfNonExisting(id, ("No such employee by id "+id), repo);
        Employee actual = repo.getOne(id);
        actual.set(employee);
        repo.save(actual);
    }

    @Override
    public List<Employee> getAll() {
        return repo.findAll();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public Employee get(Long id) {
        exceptionIfNonExisting(id, ("No such employee by id "+id), repo);
        return repo.findById(id).get();
    }

    @Override
    public  <T, K> void exceptionIfNonExisting(K id, String message, JpaRepository<T, K> repository) throws RuntimeException {
        if (!repository.existsById(id)){throw new NoEntityException(message);}
    }
}
