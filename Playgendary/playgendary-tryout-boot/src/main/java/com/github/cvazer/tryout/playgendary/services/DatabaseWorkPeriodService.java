package com.github.cvazer.tryout.playgendary.services;

import com.github.cvazer.tryout.playgendary.exceptions.NoEntityException;
import com.github.cvazer.tryout.playgendary.model.WorkPeriod;
import com.github.cvazer.tryout.playgendary.repositories.WorkPeriodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseWorkPeriodService implements WorkPeriodService{

    private WorkPeriodRepo repo;

    @Autowired
    public DatabaseWorkPeriodService(WorkPeriodRepo repo) {
        this.repo = repo;
    }

    @Override
    public void save(WorkPeriod period) {
        repo.save(period);
    }

    @Override
    public void edit(Long id, WorkPeriod period) {
        exceptionIfNonExisting(id, ("No working period with id "+id), repo);
        WorkPeriod actual = repo.getOne(id);
        actual.set(period);
        repo.save(actual);
    }

    @Override
    public void delete(Long id) {
        exceptionIfNonExisting(id, ("No working period with id "+id), repo);
        repo.deleteById(id);
    }

    @Override
    public List<WorkPeriod> getAll() {
        return repo.findAll();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public WorkPeriod get(Long id) {
        exceptionIfNonExisting(id, ("No working period with id "+id), repo);
        return repo.findById(id).get();
    }

    @Override
    public <T, K> void exceptionIfNonExisting(K id, String message, JpaRepository<T, K> repository) throws RuntimeException {
        if (!repository.existsById(id)){throw new NoEntityException(message);}
    }
}
