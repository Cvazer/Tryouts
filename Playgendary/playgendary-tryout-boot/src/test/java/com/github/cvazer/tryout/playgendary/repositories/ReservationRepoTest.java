package com.github.cvazer.tryout.playgendary.repositories;

import com.github.cvazer.tryout.playgendary.model.Employee;
import com.github.cvazer.tryout.playgendary.model.Reservation;
import com.github.cvazer.tryout.playgendary.model.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReservationRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReservationRepo repo;

    @Test
    public void allByEmployee(){
        Employee employee = new Employee("Y", "F", "K");
        Room room = new Room("R");
        Reservation reservation = new Reservation(
                employee, room,
                LocalDateTime.of(2018, 1, 1, 0, 0),
                LocalDateTime.of(2018, 1, 1, 1, 0)
        );
        entityManager.persist(reservation);
        List<Reservation> persistedList = repo.findAllByEmployee(employee);
        assertEquals("Y", persistedList.get(0).getEmployee().getFirstName());
    }

    @Test
    public void allByRoom(){
        Employee employee = new Employee("J", "R", "A");
        Room room = new Room("R2");
        Reservation reservation = new Reservation(
                employee, room,
                LocalDateTime.of(2018, 1, 2, 0, 0),
                LocalDateTime.of(2018, 1, 2, 1, 0)
        );
        entityManager.persist(reservation);
        List<Reservation> persistedList = repo.findAllByRoom(room);
        assertEquals("R2", persistedList.get(0).getRoom().getName());
    }

    @Test
    public void existsByRoomAndPeriod(){
        Employee employee = new Employee("A", "B", "C");
        LocalDateTime start = LocalDateTime.of(2018, 1, 3, 0, 0);
        LocalDateTime end = LocalDateTime.of(2018, 1, 3, 1, 0);
        Room room = new Room("R3");
        Reservation reservation = new Reservation(employee, room, start, end);
        entityManager.persist(reservation);
        assertTrue(repo.existsByRoomAndStartLessThanEqualAndEndGreaterThanEqual(room, start, start));
        assertTrue(repo.existsByRoomAndStartLessThanEqualAndEndGreaterThanEqual(room, end, end));
    }

    @Test
    public void getAllForEmployeeByPeriod(){
        Employee employee = new Employee("D", "E", "F");
        LocalDateTime start = LocalDateTime.of(2018, 1, 4, 0, 0);
        LocalDateTime end = LocalDateTime.of(2018, 1, 4, 1, 0);
        Room room = new Room("R4");
        Reservation reservation = new Reservation(employee, room, start, end);
        entityManager.persist(reservation);
        assertEquals(0, repo.findAllByEmployeeAndStartGreaterThanEqualAndEndLessThanEqual(employee, end.plusHours(1), end.plusHours(2)).size());
    }
}
