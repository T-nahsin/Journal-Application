package net.tNahsin.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/health-check")
@RestController
public class HealthCheck {

    public String healthCheck(){
        return "Hell! yeah important 3:16 days ";
    }
}
