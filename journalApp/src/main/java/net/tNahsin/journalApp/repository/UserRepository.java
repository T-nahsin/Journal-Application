package net.tNahsin.journalApp.repository;

import net.tNahsin.journalApp.entity.JournalEntry;
import net.tNahsin.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
}
