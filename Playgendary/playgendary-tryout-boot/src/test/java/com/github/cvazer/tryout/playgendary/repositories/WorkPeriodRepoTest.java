package com.github.cvazer.tryout.playgendary.repositories;

import com.github.cvazer.tryout.playgendary.model.WorkPeriod;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WorkPeriodRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private WorkPeriodRepo repo;

    @Test
    public void periodForName(){
        LocalTime start = LocalTime.of(0, 0);
        WorkPeriod period = new WorkPeriod(start, start.plusHours(1), "DAY_OF_WEEK");
        entityManager.persist(period);
        assertTrue(repo.existsByNameAndStartLessThanEqualAndEndGreaterThanEqual("DAY_OF_WEEK", start, start.plusMinutes(30)));
    }
}
