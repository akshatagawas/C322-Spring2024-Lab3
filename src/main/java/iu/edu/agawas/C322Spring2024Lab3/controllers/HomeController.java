package iu.edu.agawas.C322Spring2024Lab3.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String greetings(){
        return "Welcome to the Animal Service!";
    }
}
