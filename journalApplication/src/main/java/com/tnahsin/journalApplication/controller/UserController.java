package com.tnahsin.journalApplication.controller;

import com.tnahsin.journalApplication.DTO.EmailRequest;
import com.tnahsin.journalApplication.entity.User;
import com.tnahsin.journalApplication.repository.UserRepository;
import com.tnahsin.journalApplication.service.EmailService;
import com.tnahsin.journalApplication.service.UserService;
import com.tnahsin.journalApplication.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    WeatherService weatherService;

    @Autowired
    UserRepository userRepository ;

    @Autowired
    private UserService userService;

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User userInDb = userService.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveNewEntry(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(auth.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("get-weather/{city}")
    public ResponseEntity<?> greetings(@PathVariable String city){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            return new ResponseEntity<>("hi  " + auth.getName() + " , Weather feels like " +
                    weatherService.getWeather(city).getCurrent().getFeelslike(), HttpStatus.OK);
        }catch(Exception e) {
            log.error("Api cant be fetched");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Autowired
    EmailService emailService;

    @PostMapping("/send-mail")
    public String sendMail(@RequestBody EmailRequest request) {
        emailService.sendEmail(request.getTo(), request.getSubject(), request.getBody());
        return "Email sent successfully";
    }
}