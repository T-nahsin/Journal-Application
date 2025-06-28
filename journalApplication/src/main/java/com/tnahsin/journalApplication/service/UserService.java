package com.tnahsin.journalApplication.service;

import com.tnahsin.journalApplication.entity.User;
import com.tnahsin.journalApplication.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

     PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

     private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void saveNewEntry(User user ){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("user"));
            userRepository.save(user);
        }catch(Exception e){
            log.info("ahahahhahaaaa {}" +e);
        }
    }


   public void saveEntry(User userEntry) {
        userRepository.save(userEntry);}

    public List<User> getAll(){
        return userRepository.findAll();
    }


    public Optional<User> getEntry(ObjectId myId) {
        return userRepository.findById(myId);
    }

    public void deleteEntry(ObjectId myId) {
        userRepository.deleteById(myId);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("user" , "admin"));
        userRepository.save(user);
    }
}
