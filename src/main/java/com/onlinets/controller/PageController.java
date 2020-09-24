package com.onlinets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/to")
public class PageController {

    @RequestMapping(value = "/login")
    public String tologin(){
        return "login";
    }
    @RequestMapping(value = "/index")
    public String toindex(){
        return "index";
    }

    @RequestMapping(value = "/courseindex")
    public String tocourse(){
        return "courseindex";
    }

    @RequestMapping(value = "/set")
    public String toset(){
        return "set";
    }

    @RequestMapping(value = "/homework")
    public String tohomework(){
        return "homework";
    }

    @RequestMapping(value = "/content")
    public String tocontent(){
        return "content";
    }
}

