package com.github.cvazer.tryout.playgendary.controllers;

import com.github.cvazer.tryout.playgendary.model.Room;
import com.github.cvazer.tryout.playgendary.services.RoomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RoomController.class)
public class RoomControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RoomService service;

    @Test
    public void getAll() throws Exception {
        Room room = new Room("R");
        given(service.getAll())
                .willReturn(Collections.singletonList(room));
        mvc.perform(get("/room/getAll")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":null,\"name\":\"R\"}]"));
    }

    @Test
    public void getById() throws Exception {
        Room room = new Room("R4");
        given(service.get(anyLong()))
                .willReturn(room);
        mvc.perform(get("/room/get/4")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":null,\"name\":\"R4\"}"));
    }

    @Test
    public void add() throws Exception {
        mvc.perform(put("/room/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"R2\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void edit() throws Exception {
        mvc.perform(patch("/room/edit/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"R3\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteById() throws Exception {
        mvc.perform(delete("/room/delete/1"))
                .andExpect(status().isOk());
    }
}
