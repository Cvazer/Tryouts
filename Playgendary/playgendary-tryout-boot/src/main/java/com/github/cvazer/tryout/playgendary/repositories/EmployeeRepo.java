package com.github.cvazer.tryout.playgendary.repositories;

import com.github.cvazer.tryout.playgendary.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
