package com.github.cvazer.tryout.playgendary.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String firstName, lastName, surname;

    public Employee() {}

    public Employee(String firstName, String lastName, String surname) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.surname = surname;
    }

    public void set(Employee employee){
        if (employee.firstName!=null){firstName = employee.firstName;}
        if (employee.lastName!=null){lastName = employee.lastName;}
        if (employee.surname!=null){surname = employee.surname;}
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
