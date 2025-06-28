package com.tnahsin.journalApplication.Scheduler;


import com.tnahsin.journalApplication.entity.JournalEntry;
import com.tnahsin.journalApplication.entity.User;
import com.tnahsin.journalApplication.repository.UserRepositoryImpl;
import com.tnahsin.journalApplication.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserScheduler {
    @Autowired
    EmailService emailService;

    @Autowired
    UserRepositoryImpl userRepository;


    @Scheduled(cron = "0 * * * * *")
    public void fetchUsersToSendEmail(){

        List<User> users = userRepository.getUserForSA();

        for(User user : users) {
            List<JournalEntry>list = user.getJournalEntries();
            String email =  user.getEmail();

            if(email == null){
                continue;
            }
            emailService.sendEmail(email , list.toString() , "ALL YOUR ENTRIES");
        }
    }
}

