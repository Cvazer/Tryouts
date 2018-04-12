package com.github.cvazer.beatdev.tryout.servicies.interfaces;

import com.github.cvazer.beatdev.tryout.misc.UserStatusToken;
import com.github.cvazer.beatdev.tryout.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User get(Long id);
    Long save(User user);
    void delete(Long id);
    String getStatus(Long id);
    UserStatusToken setStatus(Long id, String status);
}
