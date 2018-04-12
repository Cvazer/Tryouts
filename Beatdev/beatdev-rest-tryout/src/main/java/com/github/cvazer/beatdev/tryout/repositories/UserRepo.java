package com.github.cvazer.beatdev.tryout.repositories;

import com.github.cvazer.beatdev.tryout.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
