package com.github.cvazer.beatdev.tryout.servicies;

import com.github.cvazer.beatdev.tryout.model.User;
import com.github.cvazer.beatdev.tryout.repositories.UserRepo;
import com.github.cvazer.beatdev.tryout.servicies.interfaces.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseUserServiceTest {

    private @MockBean UserRepo repo;
    private @Autowired UserService service;


    @Test
    public void getAllUsers(){
        List<User> users = Collections.singletonList(mock(User.class));
        when(repo.findAll()).thenReturn(users);
        assertEquals(users, service.getAll());
    }

    @Test
    public void getUserById(){
        User user = mock(User.class);
        when(repo.getOne(anyLong())).thenReturn(user);
        when(repo.existsById(anyLong())).thenReturn(true);
        assertEquals(user, service.get(anyLong()));
    }

    @Test
    public void saveUser(){
        User user = mock(User.class);
        when(repo.existsById(anyLong())).thenReturn(true);
        service.save(user);
        verify(repo, times(1)).save(user);
    }

    @Test
    public void deleteUser(){
        when(repo.existsById(anyLong())).thenReturn(true);
        service.delete(1L);
        verify(repo, times(1)).deleteById(1L);
    }

    @Test
    public void getStatusById(){
        when(repo.existsById(anyLong())).thenReturn(true);
        when(repo.getOne(anyLong())).thenReturn(mock(User.class));
        service.getStatus(2L);
        verify(repo, times(1)).getOne(2L);
    }

    @Test
    public void setStatusById(){
        when(repo.existsById(anyLong())).thenReturn(true);
        User user = mock(User.class);
        when(repo.getOne(anyLong())).thenReturn(user);
        service.setStatus(3L, "ONLINE");
        verify(repo, times(1)).getOne(3L);
        verify(repo, times(1)).save(user);
    }
}