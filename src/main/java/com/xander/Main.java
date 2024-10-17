package com.xander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
//This means anything that has getmapping in the cod will be expose to rest endpoints that clients can call
//important to say that it is a spring boot
public class    Main {
    public static void main (String[] args){
        SpringApplication.run(Main.class,args);
//        this is important to run spring boot
    }
    @GetMapping("/")
    public String greet(){
        return "Hello";
    }

//    local host 8080/congrats
//    This will return a JSON response
    @GetMapping("/congrats")
    public CongratsResponse congrats(){
        return new CongratsResponse("Congrats");
//        This is the value
    }

    //this returns the JSON
    record CongratsResponse(String congratulatingyou){
//        This is the key

    }
}
