package com.github.cvazer.tryout.playgendary.services;

import com.github.cvazer.tryout.playgendary.model.WorkPeriod;

import java.util.List;

public interface WorkPeriodService extends Service{
    void save(WorkPeriod period);
    void edit(Long id, WorkPeriod period);
    void delete(Long id);
    List<WorkPeriod> getAll();
    WorkPeriod get(Long id);
}
