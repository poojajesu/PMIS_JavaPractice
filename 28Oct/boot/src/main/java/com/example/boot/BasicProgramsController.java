package com.spring.boot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicProgramsController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Pooja! Your Spring Boot API is working!";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Spring Boot!";
    }

    @GetMapping("/addition")
    public String addition() {
        int a = 10;
        int b = 20;
        return "Sum = " + (a + b);
    }
}