package com.github.cvazer.tryout.playgendary.repositories;

import com.github.cvazer.tryout.playgendary.model.WorkPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

public interface WorkPeriodRepo extends JpaRepository<WorkPeriod, Long> {
    boolean existsByNameAndStartLessThanEqualAndEndGreaterThanEqual(String name, LocalTime lowBorder, LocalTime highBorder);
}
