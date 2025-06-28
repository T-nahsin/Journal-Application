package com.tnahsin.journalApplication.repository;

import com.tnahsin.journalApplication.entity.ConfigJournalApp;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalApp , ObjectId> {

}
