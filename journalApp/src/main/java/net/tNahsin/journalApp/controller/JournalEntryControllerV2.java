package net.tNahsin.journalApp.controller;



import net.tNahsin.journalApp.entity.JournalEntry;
import net.tNahsin.journalApp.entity.User;
import net.tNahsin.journalApp.service.JournalEntryService;
import net.tNahsin.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
        User user = userService.findByUserName(userName);
        List<JournalEntry> list =  user.getJournalEntries();
        if(list != null) return new ResponseEntity<>(list,HttpStatus.FOUND);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry , @PathVariable String userName){
        try{
            entry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(entry,userName);
            return new ResponseEntity<>(entry,HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getEntry(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.getEntry(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{userName}/{myId}")
    public ResponseEntity<JournalEntry> deleteEntry(@PathVariable String userName , @PathVariable ObjectId myId) {
        journalEntryService.deleteEntry(userName,myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{userName}/{myId}")
    public ResponseEntity<JournalEntry> updateEntry(@RequestBody JournalEntry entry, @PathVariable ObjectId myId, @PathVariable String userName) {
        JournalEntry old = journalEntryService.getEntry(myId).orElse(null);
        if(old != null) {
            old.setTitle(entry.getTitle() != null && !entry.getTitle().isEmpty() ? entry.getTitle(): old.getTitle());
            old.setContent(entry.getContent() != null && !entry.getContent().isEmpty() ?entry.getContent():old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}