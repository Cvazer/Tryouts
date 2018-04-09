package com.github.cvazer.tryout.playgendary.controllers;

import com.github.cvazer.tryout.playgendary.model.WorkPeriod;
import com.github.cvazer.tryout.playgendary.services.WorkPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workPeriod")
@Transactional
public class WorkPeriodController {

    private WorkPeriodService service;

    @Autowired
    public WorkPeriodController(WorkPeriodService service) {
        this.service = service;
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody WorkPeriod period){
        service.save(period);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void edit(@PathVariable Long id, @RequestBody WorkPeriod period){
        service.edit(id, period);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WorkPeriod> getAll(){
        return service.getAll();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WorkPeriod get(@PathVariable Long id){
        return service.get(id);
    }
}
