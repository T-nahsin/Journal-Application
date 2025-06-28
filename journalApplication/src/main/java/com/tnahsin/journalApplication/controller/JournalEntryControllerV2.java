package com.tnahsin.journalApplication.controller;




import com.tnahsin.journalApplication.entity.JournalEntry;
import com.tnahsin.journalApplication.entity.User;
import com.tnahsin.journalApplication.service.JournalEntryService;
import com.tnahsin.journalApplication.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> list =  user.getJournalEntries();
        if(list != null) return new ResponseEntity<>(list,HttpStatus.FOUND);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry  ){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userName = auth.getName();
            entry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(entry,userName);
            return new ResponseEntity<>(entry,HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getEntry(@PathVariable ObjectId myId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
         User user = userService.findByUserName(userName);
         List<JournalEntry> collect = user.getJournalEntries().stream().filter(x-> x.getId().equals(myId)).collect(Collectors.toList());
         if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.getEntry(myId);
            if(journalEntry.isPresent())return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("id/{myId}")
    public ResponseEntity<JournalEntry> deleteEntry(@PathVariable ObjectId myId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        boolean res =  journalEntryService.deleteEntry(userName,myId);
        if(res)
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("id/{myId}")
    public ResponseEntity<JournalEntry> updateEntry(@RequestBody JournalEntry entry, @PathVariable ObjectId myId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User user  = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x-> x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.getEntry(myId);
            if(journalEntry.isPresent()){
                JournalEntry old = journalEntry.get();
                old.setTitle(entry.getTitle() != null && !entry.getTitle().isEmpty() ? entry.getTitle(): old.getTitle());
                old.setContent(entry.getContent() != null && !entry.getContent().isEmpty() ?entry.getContent():old.getContent());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(old,HttpStatus.FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
