package com.jhaempyre.journalApp.entity;

import com.mongodb.lang.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

public class User {


    @Id
    private ObjectId id ;
    @NonNull
    @Indexed(unique = true)
    private String userName;
    @NonNull
    private String passWord;
    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    @NonNull
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(@NonNull String passWord) {
        this.passWord = passWord;
    }

    public List<JournalEntry> getJournalEntries() {
        return journalEntries;
    }

    public void setJournalEntries(List<JournalEntry> journalEntries) {
        this.journalEntries = journalEntries;
    }
}
