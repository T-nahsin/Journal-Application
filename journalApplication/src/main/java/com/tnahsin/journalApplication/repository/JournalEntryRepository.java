package com.tnahsin.journalApplication.repository;

import com.tnahsin.journalApplication.entity.JournalEntry;
import com.tnahsin.journalApplication.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

}
