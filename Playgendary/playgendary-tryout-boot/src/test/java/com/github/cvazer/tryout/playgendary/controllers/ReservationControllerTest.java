package com.github.cvazer.tryout.playgendary.controllers;

import com.github.cvazer.tryout.playgendary.model.Employee;
import com.github.cvazer.tryout.playgendary.model.Reservation;
import com.github.cvazer.tryout.playgendary.model.Room;
import com.github.cvazer.tryout.playgendary.services.ReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationService service;

    @Test
    public void getAll() throws Exception {
        Reservation reservation = new Reservation(
                new Employee("Y", "F", "K"),
                new Room("R"),
                LocalDateTime.of(2018, 4, 1, 0, 0),
                LocalDateTime.of(2018, 4, 1, 0, 0));
        given(service.getAll())
                .willReturn(Collections.singletonList(reservation));
        mvc.perform(get("/reservation/getAll")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":null,\"employee\":" +
                        "{\"id\":null,\"firstName\":\"Y\",\"lastName\":\"F\",\"surname\":\"K\"}," +
                        "\"room\":{\"id\":null,\"name\":\"R\"}," +
                        "\"start\":\"00:00 01-04-2018\"," +
                        "\"end\":\"00:00 01-04-2018\"}]"));
    }

    @Test
    public void getByEmployee() throws Exception {
        Reservation reservation = new Reservation(
                new Employee("J", "R", "A"),
                new Room("R2"),
                LocalDateTime.of(2018, 4, 3, 0, 0),
                LocalDateTime.of(2018, 4, 3, 0, 0));
        given(service.getAllByEmployee(anyLong()))
                .willReturn(Collections.singletonList(reservation));
        mvc.perform(get("/reservation/getByEmployee/0")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":null,\"employee\":" +
                        "{\"id\":null,\"firstName\":\"J\",\"lastName\":\"R\",\"surname\":\"A\"}," +
                        "\"room\":{\"id\":null,\"name\":\"R2\"}," +
                        "\"start\":\"00:00 03-04-2018\"," +
                        "\"end\":\"00:00 03-04-2018\"}]"));

    }

    @Test
    public void getByEmployeeAndPeriod() throws Exception {
        Reservation reservation = new Reservation(
                new Employee("G", "D", "U"),
                new Room("R3"),
                LocalDateTime.of(2018, 4, 5, 0, 0),
                LocalDateTime.of(2018, 4, 5, 0, 0));
        given(service.getAllByEmployeeAndPeriod(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .willReturn(Collections.singletonList(reservation));
        mvc.perform(post("/reservation/getByEmployeeAndPeriod/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"start\":\"00:00 05-04-2018\", \"end\":\"24:00 05-04-2018\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":null,\"employee\":" +
                        "{\"id\":null,\"firstName\":\"G\",\"lastName\":\"D\",\"surname\":\"U\"}," +
                        "\"room\":{\"id\":null,\"name\":\"R3\"}," +
                        "\"start\":\"00:00 05-04-2018\"," +
                        "\"end\":\"00:00 05-04-2018\"}]"));

    }

    @Test
    public void getByRoom() throws Exception {
        Reservation reservation = new Reservation(
                new Employee("A", "B", "C"),
                new Room("R4"),
                LocalDateTime.of(2018, 4, 6, 0, 0),
                LocalDateTime.of(2018, 4, 6, 0, 0));
        given(service.getAllByRoom(anyLong()))
                .willReturn(Collections.singletonList(reservation));
        mvc.perform(get("/reservation/getByRoom/2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":null,\"employee\":" +
                        "{\"id\":null,\"firstName\":\"A\",\"lastName\":\"B\",\"surname\":\"C\"}," +
                        "\"room\":{\"id\":null,\"name\":\"R4\"}," +
                        "\"start\":\"00:00 06-04-2018\"," +
                        "\"end\":\"00:00 06-04-2018\"}]"));
    }

    @Test
    public void getById() throws Exception {
        Reservation reservation = new Reservation(
                new Employee("D", "E", "F"),
                new Room("R5"),
                LocalDateTime.of(2018, 4, 7, 0, 0),
                LocalDateTime.of(2018, 4, 7, 0, 0));
        given(service.get(anyLong()))
                .willReturn(reservation);
        mvc.perform(get("/reservation/get/3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":null,\"employee\":" +
                        "{\"id\":null,\"firstName\":\"D\",\"lastName\":\"E\",\"surname\":\"F\"}," +
                        "\"room\":{\"id\":null,\"name\":\"R5\"}," +
                        "\"start\":\"00:00 07-04-2018\"," +
                        "\"end\":\"00:00 07-04-2018\"}"));
    }

    @Test
    public void addNew() throws Exception {
        mvc.perform(put("/reservation/add/1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"start\":\"00:00 01-04-2018\", \"end\":\"00:00 01-04-2018\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void edit() throws Exception {
        mvc.perform(patch("/reservation/edit/4")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"start\":\"00:00 02-04-2018\", \"end\":\"00:00 02-04-2018\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteReservation() throws Exception {
        mvc.perform(delete("/reservation/delete/5"))
                .andExpect(status().isOk());
    }
}
