package com.jhaempyre.journalApp.service;

import com.jhaempyre.journalApp.entity.User;
import com.jhaempyre.journalApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@Service
@Component
public class UserEntryService {
    @Autowired
    private UserEntryRepository userEntryRepository;

    public List<User> getAllUser(){
       return  userEntryRepository.findAll();
    }

    public User addUser(User user){
        return userEntryRepository.save(user);
    }

    public Optional<User> getById(ObjectId Id){
        Optional<User> user = userEntryRepository.findById(Id);
        return user;
    }

    public Optional<User> deleteById(ObjectId Id){
        Optional<User> olduser = userEntryRepository.findById(Id);
        userEntryRepository.deleteById(Id);
        return olduser;

    }

    public User updateUserDetails(User user){
        Optional<User> oldUserOptional = userEntryRepository.findByUserName(user.getUserName());

        if (oldUserOptional.isPresent()) {
            User oldUser = oldUserOptional.get();

            // Update username and password
            oldUser.setUserName(user.getUserName());
            oldUser.setPassWord(user.getPassWord()); // Consider hashing password

            // Save updated user and return it
            return userEntryRepository.save(oldUser);
        }

        return null; // Return null if user not found (better to handle properly)

    }

}
