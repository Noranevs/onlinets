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

}

