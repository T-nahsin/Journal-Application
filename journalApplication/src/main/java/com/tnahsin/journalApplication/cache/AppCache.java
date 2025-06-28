package com.tnahsin.journalApplication.cache;

import com.tnahsin.journalApplication.entity.ConfigJournalApp;
import com.tnahsin.journalApplication.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class AppCache {

    @Autowired
    ConfigJournalAppRepository configJournalAppRepository;



    public Map<String , String> APP_CACHE = new HashMap<>();


    @PostConstruct()
    public void init(){
        List<ConfigJournalApp> all = configJournalAppRepository.findAll();
        for(ConfigJournalApp con : all) {
            APP_CACHE.put(con.getKey() , con.getValue());
        }
    }
}
