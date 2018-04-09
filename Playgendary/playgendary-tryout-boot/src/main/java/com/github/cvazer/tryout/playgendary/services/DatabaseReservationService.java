package com.github.cvazer.tryout.playgendary.services;

import com.github.cvazer.tryout.playgendary.exceptions.NoEntityException;
import com.github.cvazer.tryout.playgendary.exceptions.TemporalException;
import com.github.cvazer.tryout.playgendary.model.Reservation;
import com.github.cvazer.tryout.playgendary.repositories.EmployeeRepo;
import com.github.cvazer.tryout.playgendary.repositories.ReservationRepo;
import com.github.cvazer.tryout.playgendary.repositories.RoomRepo;
import com.github.cvazer.tryout.playgendary.repositories.WorkPeriodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class DatabaseReservationService implements ReservationService{

    private EmployeeRepo employeeRepo;
    private ReservationRepo reservationRepo;
    private RoomRepo roomRepo;
    private WorkPeriodRepo periodRepo;

    @Autowired
    public DatabaseReservationService(EmployeeRepo employeeRepo, ReservationRepo reservationRepo, RoomRepo roomRepo, WorkPeriodRepo periodRepo) {
        this.employeeRepo = employeeRepo;
        this.reservationRepo = reservationRepo;
        this.roomRepo = roomRepo;
        this.periodRepo = periodRepo;
    }

    @Override
    public void save(Reservation reservation, Long employeeId, Long roomId) throws RuntimeException{
        exceptionIfNonExisting(employeeId, ("No employee with id "+employeeId), employeeRepo);
        exceptionIfNonExisting(roomId, ("No room with id "+roomId), roomRepo);

        if (reservation.getStart()==null
                ||reservation.getEnd()==null
                ||reservation.getStart().compareTo(reservation.getEnd())>0)
        {throw new TemporalException("Invalid time period");}

        if (reservation.getStart().getDayOfWeek()!=reservation.getEnd().getDayOfWeek()){throw new TemporalException("Invalid time period");}

        if (!periodRepo.existsByNameAndStartLessThanEqualAndEndGreaterThanEqual(
                reservation.getStart().getDayOfWeek().toString(),
                LocalTime.from(reservation.getStart()),
                LocalTime.from(reservation.getEnd())
        )){throw new TemporalException("There is no work hours in company for that period");}

        if (reservationRepo.existsByRoomAndStartLessThanEqualAndEndGreaterThanEqual(
                        roomRepo.getOne(roomId),
                        reservation.getStart(),
                        reservation.getStart())
                        ||reservationRepo.existsByRoomAndStartLessThanEqualAndEndGreaterThanEqual(
                        roomRepo.getOne(roomId),
                        reservation.getEnd(),
                        reservation.getEnd())
                ){throw new TemporalException("This time for room with id "+roomId+" is already occupied");}

        reservation.setEmployee(employeeRepo.getOne(employeeId));
        reservation.setRoom(roomRepo.getOne(roomId));
        reservationRepo.save(reservation);
    }

    @Override
    public void edit(Reservation reservation, Long id) {
        exceptionIfNonExisting(id, ("No reservation with id "+id), reservationRepo);
        Reservation actual = reservationRepo.getOne(id);
        actual.set(reservation);
        reservationRepo.save(actual);
    }

    @Override
    public void delete(Long id) {
        exceptionIfNonExisting(id, ("No reservation with id "+id), reservationRepo);
        reservationRepo.deleteById(id);
    }

    @Override
    public List<Reservation> getAll() {
        return reservationRepo.findAll();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public Reservation get(Long id) {
        exceptionIfNonExisting(id, ("No reservation with id "+id), reservationRepo);
        return reservationRepo.findById(id).get();
    }

    @Override
    public List<Reservation> getAllByEmployee(Long employeeId) {
        exceptionIfNonExisting(employeeId, ("No employee with id "+employeeId), employeeRepo);
        return reservationRepo.findAllByEmployee(employeeRepo.getOne(employeeId));
    }

    @Override
    public List<Reservation> getAllByRoom(Long roomId) {
        exceptionIfNonExisting(roomId, ("No room with id "+roomId), roomRepo);
        return reservationRepo.findAllByRoom(roomRepo.getOne(roomId));
    }

    @Override
    public List<Reservation> getAllByEmployeeAndPeriod(Long employeeId, LocalDateTime lowBorder, LocalDateTime highBorder) {
        exceptionIfNonExisting(employeeId,("No employee with id "+employeeId), employeeRepo);
        if (lowBorder==null||highBorder==null){throw new TemporalException("Invalid time period");}
        return reservationRepo.findAllByEmployeeAndStartGreaterThanEqualAndEndLessThanEqual(employeeRepo.getOne(employeeId),lowBorder, highBorder);
    }

    @Override
    public  <T, K> void exceptionIfNonExisting(K id, String message, JpaRepository<T, K> repository) throws RuntimeException {
        if (!repository.existsById(id)){throw new NoEntityException(message);}
    }
}
