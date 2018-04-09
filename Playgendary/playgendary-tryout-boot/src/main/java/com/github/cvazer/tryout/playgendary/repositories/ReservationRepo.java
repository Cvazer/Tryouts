package com.github.cvazer.tryout.playgendary.repositories;

import com.github.cvazer.tryout.playgendary.model.Employee;
import com.github.cvazer.tryout.playgendary.model.Reservation;
import com.github.cvazer.tryout.playgendary.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByEmployee(Employee employee);
    List<Reservation> findAllByRoom(Room room);
    boolean existsByRoomAndStartLessThanEqualAndEndGreaterThanEqual(Room room, LocalDateTime lowBorder, LocalDateTime highBorder);
    List<Reservation> findAllByEmployeeAndStartGreaterThanEqualAndEndLessThanEqual(Employee employee, LocalDateTime lowBorder, LocalDateTime highBorder);
}
