package com.github.cvazer.tryout.playgendary.services;

import com.github.cvazer.tryout.playgendary.model.Room;

import java.util.List;

public interface RoomService extends Service{
    void save(Room room);
    void delete(Long id);
    void edit(Room room, Long id);
    List<Room> getAll();
    Room get(Long id);
}
