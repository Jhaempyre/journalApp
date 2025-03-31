package com.jhaempyre.journalApp.repository;

import com.jhaempyre.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserEntryRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findByUserName(String userName);
}
