package com.github.cvazer.tryout.playgendary.configurations;

import com.github.cvazer.tryout.playgendary.model.Employee;
import com.github.cvazer.tryout.playgendary.model.Reservation;
import com.github.cvazer.tryout.playgendary.model.Room;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.util.function.Supplier;

@Configuration
public class ModelBeansConfiguration {

    @Bean @Lazy public Supplier<Reservation> reservationSupplier(){
        return this::reservation;
    }
    @Bean @Lazy public Supplier<Employee> employeeSupplier(){
        return this::employee;
    }
    @Bean @Lazy public Supplier<Room> roomSupplier(){
        return this::room;
    }
    @Bean @Scope("prototype") public Employee employee(){
        return new Employee();
    }
    @Bean @Lazy @Scope("prototype") public Room room(){
        return new Room();
    }
    @Bean @Lazy @Scope("prototype") public Reservation reservation(){
        return new Reservation();
    }

}
