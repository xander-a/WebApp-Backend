package com.xander;
//Getting objects into object in JSON
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SpringBootApplication

public class secondMain {
    public static void main (String[] args){
    SpringApplication.run(Main.class,args);
//        this is important to run spring boot
}

    @GetMapping("/greet")
    public GreetResponse greet(){
        return  new GreetResponse("Hello",
                List.of("Java", "Python", "SQL"),
                new Person("Xander", 28, 1000000));
    }

    record  Person(String name,
                   int age,
                   double savings){

    }
    record GreetResponse(
            String greet,
            List<String> favProgrammingLanguage,
            Person person
    ){

    }


}
