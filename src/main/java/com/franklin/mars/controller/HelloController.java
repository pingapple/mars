package com.franklin.mars.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }


    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello";
    }


    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
