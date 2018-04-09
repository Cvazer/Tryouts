package com.github.cvazer.tryout.playgendary.services;

import com.github.cvazer.tryout.playgendary.exceptions.NoEntityException;
import com.github.cvazer.tryout.playgendary.model.Room;
import com.github.cvazer.tryout.playgendary.repositories.RoomRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@ContextConfiguration
public class DatabaseRoomServiceTest {

    @TestConfiguration
    static class Config{
        public @Bean RoomRepo repo(){
            return mock(RoomRepo.class);
        }
        public @Bean RoomService service(){
            return new DatabaseRoomService(repo());
        }
    }

    private @Autowired RoomRepo repo;
    private @Autowired RoomService service;

    @Test
    public void saveRoom(){
        Room room = mock(Room.class);
        when(repo.save(any(Room.class))).thenReturn(null);
        service.save(room);
        verify(repo, times(1)).save(room);
    }

    @Test
    public void deleteRoom(){
        when(repo.existsById(anyLong())).thenReturn(true);
        doNothing().when(repo).deleteById(anyLong());
        service.delete(0L);
        verify(repo, times(1)).deleteById(0L);
    }

    @Test(expected = NoEntityException.class)
    public void deleteRoomFail(){
        when(repo.existsById(anyLong())).thenReturn(false);
        doNothing().when(repo).deleteById(anyLong());
        service.delete(anyLong());
    }

    @Test
    public void editRoom(){
        Room room = new Room("A");
        Room room2 = new Room("B");
        when(repo.existsById(anyLong())).thenReturn(true);
        when(repo.getOne(anyLong())).thenReturn(room);
        service.edit(room2, anyLong());
        assertEquals("B", room.getName());
    }

    @Test
    public void getAllRooms(){
        Room room = mock(Room.class);
        when(repo.findAll()).thenReturn(Collections.singletonList(room));
        assertTrue(service.getAll().stream().findFirst().isPresent());
        assertSame(room, service.getAll().stream().findFirst().get());
    }

    @Test
    public void getRoom(){
        Room room = mock(Room.class);
        when(repo.findById(anyLong())).then(invocation -> Optional.of(room));
        when(repo.existsById(anyLong())).thenReturn(true);
        assertSame(room, service.get(anyLong()));
    }

}
