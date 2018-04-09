package com.github.cvazer.tryout.playgendary.controllers;

import com.github.cvazer.tryout.playgendary.model.WorkPeriod;
import com.github.cvazer.tryout.playgendary.services.WorkPeriodService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WorkPeriodController.class)
public class WorkPeriodControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WorkPeriodService service;

    @Test
    public void getAll() throws Exception {
        WorkPeriod period = new WorkPeriod(LocalTime.of(0, 0), LocalTime.of(0, 0), "DAY_OF_WEEK");
        given(service.getAll())
                .willReturn(Collections.singletonList(period));
        mvc.perform(get("/workPeriod/getAll")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{" +
                        "\"start\":\"00:00\"," +
                        "\"end\":\"00:00\"," +
                        "\"name\":\"DAY_OF_WEEK\"}]"));
    }

    @Test
    public void getById() throws Exception {
        WorkPeriod period = new WorkPeriod(LocalTime.of(1, 0), LocalTime.of(1, 0), "DAY_OF_WEEK");
        given(service.get(anyLong()))
                .willReturn(period);
        mvc.perform(get("/workPeriod/get/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":null,\"start\":\"01:00\",\"end\":\"01:00\",\"name\":\"DAY_OF_WEEK\"}"));
    }

    @Test
    public void addNew() throws Exception {
        mvc.perform(put("/workPeriod/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"start\":\"00:00\", \"end\":\"00:00\", \"name\":\"DAY_OF_WEEK\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void edit() throws Exception {
        mvc.perform(patch("/workPeriod/edit/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"start\":\"00:00\", \"end\":\"00:00\", \"name\":\"DAY_OF_WEEK\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteById() throws Exception {
        mvc.perform(delete("/workPeriod/delete/2"))
                .andExpect(status().isOk());
    }
}
