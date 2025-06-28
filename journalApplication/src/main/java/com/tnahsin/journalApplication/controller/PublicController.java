package com.tnahsin.journalApplication.controller;


import com.tnahsin.journalApplication.entity.User;
import com.tnahsin.journalApplication.service.UserDetailsServiceImpl;
import com.tnahsin.journalApplication.service.UserService;
import com.tnahsin.journalApplication.utilis.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    private static final Logger log = LoggerFactory.getLogger(PublicController.class);


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("health-check")
    public String checkHealth(){
        return "Health-check";
    }

    @PostMapping("/sign-up")
    public void signup(@RequestBody User user) {
        userService.saveNewEntry(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails  = userDetailsService.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt , HttpStatus.OK);
        } catch (Exception e) {
            log.error("Cant authenticate");
            return new ResponseEntity<>("Incorrect Username or password" , HttpStatus.OK);
        }

    }


}
