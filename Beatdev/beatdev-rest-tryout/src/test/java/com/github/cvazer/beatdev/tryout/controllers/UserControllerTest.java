package com.github.cvazer.beatdev.tryout.controllers;

import com.github.cvazer.beatdev.tryout.misc.UserStatusToken;
import com.github.cvazer.beatdev.tryout.model.User;
import com.github.cvazer.beatdev.tryout.servicies.interfaces.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest {

    private @Autowired MockMvc mvc;

    private @MockBean UserService service;

    @Test
    public void add() throws Exception {
        given(service.save(any(User.class))).willReturn(1L);
        mvc.perform(put("/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"username\":\"test\"," +
                        "\"password\":\"test2\"," +
                        "\"email\":\"test3\"," +
                        "\"img\":\"test4\"" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }

    @Test
    public void getAll() throws Exception {
        given(service.getAll()).willReturn(Collections.singletonList(new User("t1", "t2", "t3", "t4", "t5")));
        mvc.perform(get("/user/get")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":null,\"username\":\"t1\",\"password\":\"t2\",\"email\":\"t3\",\"img\":\"t4\",\"status\":\"t5\"}]"));
        verify(service, times(1)).getAll();
    }

    @Test
    public void getById() throws Exception {
        given(service.get(anyLong())).willReturn(new User("t6", "t7", "t8", "t9", "t10"));
        mvc.perform(get("/user/get/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":null,\"username\":\"t6\",\"password\":\"t7\",\"email\":\"t8\",\"img\":\"t9\",\"status\":\"t10\"}"));
        verify(service, times(1)).get(1L);
    }

    @Test
    public void getStatusById() throws Exception {
        given(service.getStatus(anyLong())).willReturn(anyString());
        mvc.perform(get("/user/status/get/2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service, times(1)).getStatus(2L);
    }

    @Test
    public void setStatusById() throws Exception {
        given(service.setStatus(anyLong(), anyString())).willReturn(new UserStatusToken(3L, "OLD", "NEW"));
        mvc.perform(patch("/user/status/set/3/ONLINE")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service, times(1)).setStatus(3L, "ONLINE");
    }

    @Test
    public void deleteById() throws Exception {
        mvc.perform(delete("/user/delete/4"))
                .andExpect(status().isOk());
        verify(service, times(1)).delete(4L);
    }

}