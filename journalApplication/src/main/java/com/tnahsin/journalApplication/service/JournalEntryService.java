package com.tnahsin.journalApplication.service;

import com.tnahsin.journalApplication.entity.JournalEntry;
import com.tnahsin.journalApplication.entity.User;
import com.tnahsin.journalApplication.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService  userService;


    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        User user = userService.findByUserName(userName);
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }


    public Optional<JournalEntry> getEntry(ObjectId myId) {
        return journalEntryRepository.findById(myId);
    }

    @Transactional
    public boolean deleteEntry(String userName, ObjectId myId) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
             removed = user.getJournalEntries().removeIf(x -> x.getId().equals(myId));
            if (removed) {
                journalEntryRepository.deleteById(myId);
                userService.saveEntry(user);
            }
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("NOT RUNNING!!!! while deleting");
        }
        return removed;
    }


}