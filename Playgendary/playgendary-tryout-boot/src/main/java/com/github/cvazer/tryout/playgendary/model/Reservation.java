package com.github.cvazer.tryout.playgendary.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reservation {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private @ManyToOne(cascade = CascadeType.ALL) Employee employee;
    private @ManyToOne(cascade = CascadeType.ALL) Room room;
    private @JsonFormat(pattern = "HH:mm dd-MM-yyyy") LocalDateTime start;
    private @JsonFormat(pattern = "HH:mm dd-MM-yyyy") LocalDateTime end;

    public Reservation() {}

    public Reservation(Employee employee, Room room, LocalDateTime start, LocalDateTime end) {
        this.employee = employee;
        this.room = room;
        this.start = start;
        this.end = end;
    }

    public void set(Reservation reservation){
        if (reservation.employee!=null){employee = reservation.employee;}
        if (reservation.room!=null){room = reservation.room;}
        if (reservation.start!=null){start = reservation.start;}
        if (reservation.end!=null){end = reservation.end;}
    }

    public Long getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
