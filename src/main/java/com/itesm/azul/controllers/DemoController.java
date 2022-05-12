package com.itesm.azul.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/demo")
public class DemoController {

    @GetMapping("/say-hello")
    public String sayHello(){
        return "Hello world";
    }
}
