package com.github.cvazer.tryout.playgendary.services;

import com.github.cvazer.tryout.playgendary.model.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService extends Service{
    void save(Reservation reservation, Long employeeId, Long roomId) throws RuntimeException;
    void edit(Reservation reservation, Long id);
    void delete(Long id);
    List<Reservation> getAll();
    Reservation get(Long id);
    List<Reservation> getAllByEmployee(Long employeeId);
    List<Reservation> getAllByRoom(Long roomId);
    List<Reservation> getAllByEmployeeAndPeriod(Long employeeId, LocalDateTime start, LocalDateTime end);
}
