package net.tNahsin.journalApp.service;

import net.tNahsin.journalApp.entity.JournalEntry;
import net.tNahsin.journalApp.entity.User;
import net.tNahsin.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
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

    public void deleteEntry(String userName, ObjectId myId) {
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x-> x.getId().equals(myId));
        journalEntryRepository.deleteById(myId);
        userService.saveEntry(user);
    }
}
