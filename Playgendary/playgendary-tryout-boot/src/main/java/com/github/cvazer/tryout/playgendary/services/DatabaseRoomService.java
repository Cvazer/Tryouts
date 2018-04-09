package com.github.cvazer.tryout.playgendary.services;

import com.github.cvazer.tryout.playgendary.exceptions.NoEntityException;
import com.github.cvazer.tryout.playgendary.model.Room;
import com.github.cvazer.tryout.playgendary.repositories.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseRoomService implements RoomService{

    private RoomRepo repo;

    @Autowired
    public DatabaseRoomService(RoomRepo repo) {
        this.repo = repo;
    }

    @Override
    public void save(Room room) {
        repo.save(room);
    }

    @Override
    public void delete(Long id) {
        exceptionIfNonExisting(id, ("No such room by id "+id), repo);
        repo.deleteById(id);
    }

    @Override
    public void edit(Room room, Long id) {
        exceptionIfNonExisting(id, ("No such room by id "+id), repo);
        Room actual = repo.getOne(id);
        actual.set(room);
        repo.save(actual);
    }

    @Override
    public List<Room> getAll() {
        return repo.findAll();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public Room get(Long id) {
        exceptionIfNonExisting(id, ("No such room by id "+id), repo);
        return repo.findById(id).get();
    }

    @Override
    public <T, K> void exceptionIfNonExisting(K id, String message, JpaRepository<T, K> repository) throws RuntimeException {
        if (!repository.existsById(id)){throw new NoEntityException(message);}
    }
}
