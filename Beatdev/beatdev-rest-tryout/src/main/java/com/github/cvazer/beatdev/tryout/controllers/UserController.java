package com.github.cvazer.beatdev.tryout.controllers;

import com.github.cvazer.beatdev.tryout.misc.UserStatusToken;
import com.github.cvazer.beatdev.tryout.model.User;
import com.github.cvazer.beatdev.tryout.servicies.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Long add(@RequestBody User user){
        return service.save(user);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll(){
        return service.getAll();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable Long id){
        return service.get(id);
    }

    @RequestMapping(value = "/status/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getStatus(@PathVariable Long id){
        return service.getStatus(id);
    }

    @RequestMapping(value = "/status/set/{id}/{status}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserStatusToken setStatus(@PathVariable Long id, @PathVariable String status){
        return service.setStatus(id, status);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
