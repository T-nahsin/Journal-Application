package net.tNahsin.journalApp.service;


import lombok.NonNull;
import net.tNahsin.journalApp.entity.User;
import net.tNahsin.journalApp.repository.JournalEntryRepository;
import net.tNahsin.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User userEntry) {
        userRepository.save(userEntry);
    }

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
}
