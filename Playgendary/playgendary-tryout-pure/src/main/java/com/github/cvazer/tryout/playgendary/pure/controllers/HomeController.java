package com.github.cvazer.tryout.playgendary.pure.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String home(){
        return "{\"name\":\"HOME!\"}";
    }

    @RequestMapping(value = "/home2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String home2(){
        return "{\"name\":\"HOME2222!\"}";
    }

    @RequestMapping(value = "/home2/home3", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String home3(){
        return "{\"name\":\"HOME33333!\"}";
    }
}
