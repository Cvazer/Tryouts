package com.github.cvazer.beatdev.tryout.servicies;

import com.github.cvazer.beatdev.tryout.exceptions.EntityException;
import com.github.cvazer.beatdev.tryout.misc.UserStatusToken;
import com.github.cvazer.beatdev.tryout.model.User;
import com.github.cvazer.beatdev.tryout.repositories.UserRepo;
import com.github.cvazer.beatdev.tryout.servicies.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class DatabaseUserService implements UserService {

    private UserRepo userRepo;
    private Supplier<UserStatusToken> statusTokenSupplier;

    @Autowired
    public DatabaseUserService(UserRepo userRepo, Supplier<UserStatusToken> statusTokenSupplier) {
        this.userRepo = userRepo;
        this.statusTokenSupplier = statusTokenSupplier;
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public User get(Long id) {
        if (!userRepo.existsById(id)){throw new EntityException("No such user with id "+id);}
        return userRepo.getOne(id);
    }

    @Override
    public Long save(User user) {
        userRepo.save(user);
        return user.getId();
    }

    @Override
    public void delete(Long id) {
        if (!userRepo.existsById(id)){throw new EntityException("No such user with id "+id);}
        userRepo.deleteById(id);
    }

    @Override
    public String getStatus(Long id) {
        if (!userRepo.existsById(id)){throw new EntityException("No such user with id "+id);}
        return userRepo.getOne(id).getStatus();
    }

    @Override
    public UserStatusToken setStatus(Long id, String status) {
        if (!userRepo.existsById(id)){throw new EntityException("No such user with id "+id);}
        if ((!status.contentEquals("ONLINE")&&!status.contentEquals("OFFLINE"))){throw new EntityException("Invalid user status: "+status);}
        User user = userRepo.getOne(id);
        UserStatusToken token = statusTokenSupplier.get().set(user, status);
        userRepo.save(user);
        return token;
    }
}
