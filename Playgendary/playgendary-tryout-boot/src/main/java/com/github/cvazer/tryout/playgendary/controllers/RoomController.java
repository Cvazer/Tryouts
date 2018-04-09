package com.github.cvazer.tryout.playgendary.controllers;

import com.github.cvazer.tryout.playgendary.model.Room;
import com.github.cvazer.tryout.playgendary.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
@Transactional
public class RoomController {

    private RoomService service;

    @Autowired
    public RoomController(RoomService service) {
        this.service = service;
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody Room room){
        service.save(room);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@PathVariable Long id, @RequestBody Room room){
        service.edit(room, id);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Room> getAll(){
        return service.getAll();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Room getById(@PathVariable Long id){
        return service.get(id);
    }

}
