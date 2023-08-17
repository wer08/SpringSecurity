package com.wojtek.restapi.controller;

import com.wojtek.restapi.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final HelloService service;

    @GetMapping("/")
    public String hello(){
        return service.hello();
    }
}
