package com.github.cvazer.tryout.playgendary.services;

import com.github.cvazer.tryout.playgendary.exceptions.NoEntityException;
import com.github.cvazer.tryout.playgendary.exceptions.TemporalException;
import com.github.cvazer.tryout.playgendary.model.Employee;
import com.github.cvazer.tryout.playgendary.model.Reservation;
import com.github.cvazer.tryout.playgendary.model.Room;
import com.github.cvazer.tryout.playgendary.repositories.EmployeeRepo;
import com.github.cvazer.tryout.playgendary.repositories.ReservationRepo;
import com.github.cvazer.tryout.playgendary.repositories.RoomRepo;
import com.github.cvazer.tryout.playgendary.repositories.WorkPeriodRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@ContextConfiguration
public class DatabaseReservationServiceTest {

    @TestConfiguration
    static class Config{
        public @Bean ReservationRepo reservationRepo(){
            return mock(ReservationRepo.class);
        }
        public @Bean EmployeeRepo employeeRepo(){ return mock(EmployeeRepo.class); }
        public @Bean RoomRepo roomRepo(){ return mock(RoomRepo.class); }
        public @Bean WorkPeriodRepo workPeriodRepo(){ return mock(WorkPeriodRepo.class); }
        public @Bean ReservationService reservationService(){
            return new DatabaseReservationService(
                    employeeRepo(),
                    reservationRepo(),
                    roomRepo(),
                    workPeriodRepo()
            );
        }
    }

    private @Autowired ReservationRepo reservationRepo;
    private @Autowired EmployeeRepo employeeRepo;
    private @Autowired RoomRepo roomRepo;
    private @Autowired WorkPeriodRepo workPeriodRepo;
    private @Autowired ReservationService reservationService;

    @Test
    public void saveReservation(){
        when(employeeRepo.existsById(anyLong())).thenReturn(true);
        when(roomRepo.existsById(anyLong())).thenReturn(true);
        when(workPeriodRepo.existsByNameAndStartLessThanEqualAndEndGreaterThanEqual(
                anyString(),
                any(LocalTime.class),
                any(LocalTime.class)))
                .thenReturn(true);
        when(reservationRepo.existsByRoomAndStartLessThanEqualAndEndGreaterThanEqual(
                any(Room.class),
                any(LocalDateTime.class),
                any(LocalDateTime.class)))
                .thenReturn(false);
        Employee employee = new Employee("A", "B", "C");
        employee.setId(2L);
        Room room = new Room("R");
        room.setId(3L);
        LocalDateTime start = LocalDateTime.of(2018, 4, 8, 0, 0);
        LocalDateTime end = LocalDateTime.of(2018, 4, 8, 1, 0);
        Reservation reservation = new Reservation(employee, room, start, end);
        reservationService.save(reservation, employee.getId(), room.getId());
        verify(employeeRepo, times(1)).existsById(2L);
        verify(roomRepo, times(1)).existsById(3L);
        verify(reservationRepo, times(1)).save(reservation);
    }

    @Test(expected = NoEntityException.class)
    public void saveReservationFailEmployeeId(){
        when(employeeRepo.existsById(anyLong())).thenReturn(false);
        reservationService.save(mock(Reservation.class), 4L, 5L);
    }

    @Test(expected = NoEntityException.class)
    public void saveReservationFailRoomId(){
        when(roomRepo.existsById(anyLong())).thenReturn(false);
        reservationService.save(mock(Reservation.class), 6L, 7L);
    }

    @Test(expected = TemporalException.class)
    public void saveReservationFailPeriod(){
        when(employeeRepo.existsById(anyLong())).thenReturn(true);
        when(roomRepo.existsById(anyLong())).thenReturn(true);
        reservationService.save(mock(Reservation.class), 6L, 7L);
    }

    @Test(expected = TemporalException.class)
    public void saveReservationFailWorkPeriod(){
        when(employeeRepo.existsById(anyLong())).thenReturn(true);
        when(roomRepo.existsById(anyLong())).thenReturn(true);
        when(workPeriodRepo.existsByNameAndStartLessThanEqualAndEndGreaterThanEqual(
                anyString(),
                any(LocalTime.class),
                any(LocalTime.class)))
                .thenReturn(false);
        Employee employee = new Employee("A", "B", "C");
        Room room = new Room("R");
        LocalDateTime start = LocalDateTime.of(2018, 4, 8, 2, 0);
        LocalDateTime end = LocalDateTime.of(2018, 4, 8, 3, 0);
        Reservation reservation = new Reservation(employee, room, start, end);
        reservationService.save(reservation, 8L, 9L);
    }

    @Test(expected = TemporalException.class)
    public void saveReservationFailOccupied(){
        when(employeeRepo.existsById(anyLong())).thenReturn(true);
        when(roomRepo.existsById(anyLong())).thenReturn(true);
        when(workPeriodRepo.existsByNameAndStartLessThanEqualAndEndGreaterThanEqual(
                anyString(),
                any(LocalTime.class),
                any(LocalTime.class)))
                .thenReturn(true);
        when(reservationRepo.existsByRoomAndStartLessThanEqualAndEndGreaterThanEqual(
                any(Room.class),
                any(LocalDateTime.class),
                any(LocalDateTime.class)))
                .thenReturn(true);
        Employee employee = new Employee("D", "E", "F");
        Room room = new Room("R2");
        LocalDateTime start = LocalDateTime.of(2018, 4, 8, 4, 0);
        LocalDateTime end = LocalDateTime.of(2018, 4, 8, 5, 0);
        Reservation reservation = new Reservation(employee, room, start, end);
        when(roomRepo.getOne(anyLong())).thenReturn(room);
        reservationService.save(reservation, 10L, 11L);
    }

    @Test
    public void editReservation(){
        when(reservationRepo.existsById(anyLong())).thenReturn(true);

        Employee employee = mock(Employee.class);
        Room room = mock(Room.class);
        LocalDateTime start = LocalDateTime.of(2018, 4, 8, 4, 0);
        LocalDateTime end = LocalDateTime.of(2018, 4, 8, 5, 0);
        Reservation reservation = new Reservation(employee, room, start, end);

        Employee employee2 = mock(Employee.class);
        Room room2 = mock(Room.class);
        LocalDateTime start2 = LocalDateTime.of(2018, 4, 8, 6, 0);
        LocalDateTime end2 = LocalDateTime.of(2018, 4, 8, 7, 0);
        Reservation reservation2 = new Reservation(employee2, room2, start2, end2);

        when(reservationRepo.getOne(anyLong())).thenReturn(reservation);
        reservationService.edit(reservation2, anyLong());
        assertEquals(employee2, reservation.getEmployee());
        assertEquals(room2, reservation.getRoom());
        assertEquals(start2, reservation.getStart());
        assertEquals(end2, reservation.getEnd());
    }

    @Test
    public void deleteReservation(){
        when(reservationRepo.existsById(anyLong())).thenReturn(true);
        doNothing().when(reservationRepo).deleteById(anyLong());
        reservationService.delete(12L);
        verify(reservationRepo, times(1)).deleteById(12L);
    }

    @Test
    public void getAllReservations(){
        Reservation reservation = mock(Reservation.class);
        when(reservationRepo.findAll()).thenReturn(Collections.singletonList(reservation));
        assertTrue(reservationService.getAll().stream().findFirst().isPresent());
        assertSame(reservation, reservationService.getAll().stream().findFirst().get());
    }

    @Test
    public void getReservation(){
        Reservation reservation = mock(Reservation.class);
        when(reservationRepo.findById(anyLong())).then(invocation -> Optional.of(reservation));
        when(reservationRepo.existsById(anyLong())).thenReturn(true);
        assertSame(reservation, reservationService.get(anyLong()));
    }

    @Test
    public void getAllReservationsByEmployeeAndPeriod(){
        when(employeeRepo.existsById(anyLong())).thenReturn(true);

        Employee employee = mock(Employee.class);
        Room room = mock(Room.class);
        LocalDateTime start = LocalDateTime.of(2018, 4, 8, 8, 0);
        LocalDateTime end = LocalDateTime.of(2018, 4, 8, 9, 0);
        Reservation reservation = new Reservation(employee, room, start, end);

        when(employeeRepo.getOne(anyLong())).thenReturn(employee);
        when(reservationRepo.findAllByEmployeeAndStartGreaterThanEqualAndEndLessThanEqual(
                any(Employee.class),
                any(LocalDateTime.class),
                any(LocalDateTime.class)))
                .thenReturn(Collections.singletonList(reservation));

        assertTrue(reservationService.getAllByEmployeeAndPeriod(anyLong(), start, start).stream().findFirst().isPresent());
        assertSame(reservation, reservationService.getAllByEmployeeAndPeriod(anyLong(), start, start).stream().findFirst().get());
    }

    @Test
    public void getAllReservationsByRoom(){
        when(roomRepo.existsById(anyLong())).thenReturn(true);

        Employee employee = mock(Employee.class);
        Room room = mock(Room.class);
        LocalDateTime start = LocalDateTime.of(2018, 4, 8, 8, 0);
        LocalDateTime end = LocalDateTime.of(2018, 4, 8, 9, 0);
        Reservation reservation = new Reservation(employee, room, start, end);

        when(roomRepo.getOne(anyLong())).thenReturn(room);
        when(reservationRepo.findAllByRoom(any(Room.class))).thenReturn(Collections.singletonList(reservation));

        assertTrue(reservationService.getAllByRoom(anyLong()).stream().findFirst().isPresent());
        assertSame(reservation, reservationService.getAllByRoom(anyLong()).stream().findFirst().get());
    }

    @Test
    public void getAllReservationsByEmployee(){
        when(employeeRepo.existsById(anyLong())).thenReturn(true);

        Employee employee = mock(Employee.class);
        Room room = mock(Room.class);
        LocalDateTime start = LocalDateTime.of(2018, 4, 8, 10, 0);
        LocalDateTime end = LocalDateTime.of(2018, 4, 8, 11, 0);
        Reservation reservation = new Reservation(employee, room, start, end);

        when(employeeRepo.getOne(anyLong())).thenReturn(employee);
        when(reservationRepo.findAllByEmployee(any(Employee.class))).thenReturn(Collections.singletonList(reservation));

        assertTrue(reservationService.getAllByEmployee(anyLong()).stream().findFirst().isPresent());
        assertSame(reservation, reservationService.getAllByEmployee(anyLong()).stream().findFirst().get());
    }
}
