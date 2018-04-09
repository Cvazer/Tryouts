package com.github.cvazer.tryout.playgendary.services;

import com.github.cvazer.tryout.playgendary.exceptions.NoEntityException;
import com.github.cvazer.tryout.playgendary.model.WorkPeriod;
import com.github.cvazer.tryout.playgendary.repositories.WorkPeriodRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@ContextConfiguration
public class DatabaseWorkPeriodServiceTest {

    @TestConfiguration
    static class Config{
        public @Bean WorkPeriodRepo repo(){ return mock(WorkPeriodRepo.class); }
        public @Bean WorkPeriodService service(){ return new DatabaseWorkPeriodService(repo()); }
    }

    private @Autowired WorkPeriodRepo repo;
    private @Autowired WorkPeriodService service;

    @Test
    public void savePeriod(){
        WorkPeriod period = mock(WorkPeriod.class);
        when(repo.save(any(WorkPeriod.class))).thenReturn(null);
        service.save(period);
        verify(repo, times(1)).save(period);
    }

    @Test
    public void deletePeriod(){
        when(repo.existsById(anyLong())).thenReturn(true);
        doNothing().when(repo).deleteById(anyLong());
        service.delete(14L);
        verify(repo, times(1)).deleteById(14L);
    }

    @Test(expected = NoEntityException.class)
    public void deletePeriodFail(){
        when(repo.existsById(anyLong())).thenReturn(false);
        doNothing().when(repo).deleteById(anyLong());
        service.delete(anyLong());
    }

    @Test
    public void editRoom(){
        when(repo.existsById(anyLong())).thenReturn(true);

        LocalTime start = LocalTime.of(0, 0);
        LocalTime end = LocalTime.of(1, 0);
        WorkPeriod period = new WorkPeriod(start, end, "DAY_OF_WEEK");

        LocalTime start2 = LocalTime.of(2, 0);
        LocalTime end2 = LocalTime.of(3, 0);
        WorkPeriod period2 = new WorkPeriod(start2, end2, "DAY_OF_WEEK2");

        when(repo.existsById(anyLong())).thenReturn(true);
        when(repo.getOne(anyLong())).thenReturn(period);
        service.edit(anyLong(), period2);

        assertEquals(start2, period.getStart());
        assertEquals(end2, period.getEnd());
        assertEquals("DAY_OF_WEEK2", period.getName());
    }

    @Test
    public void getAllPeriods(){
        WorkPeriod period = mock(WorkPeriod.class);
        when(repo.findAll()).thenReturn(Collections.singletonList(period));
        assertTrue(service.getAll().stream().findFirst().isPresent());
        assertSame(period, service.getAll().stream().findFirst().get());
    }

    @Test
    public void getPeriod(){
        WorkPeriod period = mock(WorkPeriod.class);
        when(repo.findById(anyLong())).then(invocation -> Optional.of(period));
        when(repo.existsById(anyLong())).thenReturn(true);
        assertSame(period, service.get(anyLong()));
    }
}
