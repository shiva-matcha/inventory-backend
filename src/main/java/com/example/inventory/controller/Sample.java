package com.example.inventory.controller;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sample")
public class Sample {

    @PostMapping()
    public String  m1(){
        System.out.println("This is method 1");
        return " This is method 1";
    }

    @GetMapping ("/m2")
    public String m2(){
        System.out.println("This is method 2");
        return "Method 2";
    }

    @GetMapping ("/m3")
    public String m3(){
        System.out.println("This is method 3");
        return " Method 3";
    }

    public void m4(){
        System.out.println("This is method 4");
    }

}
