package com.github.cvazer.tryout.playgendary.repositories;

import com.github.cvazer.tryout.playgendary.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RoomRepo extends JpaRepository<Room, Long> {
}
