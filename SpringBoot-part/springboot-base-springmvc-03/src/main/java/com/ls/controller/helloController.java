package com.ls.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/hello")
@Controller
@ResponseBody
public class helloController {

    @GetMapping("/one")
    public String one(){
        return "Hello One!";
    }
}
