package com.jhaempyre.journalApp.controllers;

import com.jhaempyre.journalApp.entity.User;
import com.jhaempyre.journalApp.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

        @Autowired
        private UserEntryService userEntryService;

        @GetMapping("/getAllUser")
        public List<User> getAllUser(){
            return userEntryService.getAllUser();
        }
        @PostMapping("/addUser")
        public User addUser(@RequestBody User user){
            return userEntryService.addUser(user) ;
        }
        @GetMapping("/getById/{Id}")
        public Optional<User> getById(@PathVariable ObjectId Id){
            return userEntryService.getById(Id);
        }
        @DeleteMapping("/delete/{Id}")
        public Optional<User> deleteById(@PathVariable ObjectId Id){
            return userEntryService.deleteById(Id);
        }
        @PutMapping("/updateDetails")
        public User updateUserDetails(@RequestBody User user ){
            return userEntryService.updateUserDetails(user);
        }

}
