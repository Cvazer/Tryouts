package com.github.cvazer.tryout.playgendary.controllers;

import com.github.cvazer.tryout.playgendary.model.Reservation;
import com.github.cvazer.tryout.playgendary.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@Transactional
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(value = "/add/{employeeId}/{roomId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@PathVariable Long employeeId, @PathVariable Long roomId, @RequestBody Reservation reservation) throws RuntimeException{
        reservationService.save(reservation, employeeId, roomId);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void edit(@PathVariable Long id, @RequestBody Reservation reservation){
        reservationService.edit(reservation, id);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservation> getAll(){
        return reservationService.getAll();
    }

    @RequestMapping(value = "/getByEmployee/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservation> getByEmployee(@PathVariable Long id){
        return reservationService.getAllByEmployee(id);
    }
    @RequestMapping(value = "/getByEmployeeAndPeriod/{id}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservation> getByEmployeeAndPeriod(@PathVariable Long id, @RequestBody Reservation reservation){
        return reservationService.getAllByEmployeeAndPeriod(id, reservation.getStart(), reservation.getEnd());
    }

    @RequestMapping(value = "/getByRoom/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservation> getByRoom(@PathVariable Long id){
        return reservationService.getAllByRoom(id);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Reservation get(@PathVariable Long id){
        return reservationService.get(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        reservationService.delete(id);
    }

}
