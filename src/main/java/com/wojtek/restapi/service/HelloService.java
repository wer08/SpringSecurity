package com.wojtek.restapi.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String hello(){
        return "Hello World!";
    }

}
