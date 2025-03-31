package com.jhaempyre.journalApp.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jhaempyre.journalApp.service.ObjectIdSerializer;
import com.mongodb.lang.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
public class User {
    @Id
    @JsonSerialize(using = ObjectIdSerializer.class) // Use the custom serializer
    private ObjectId id ;
    @NonNull
    @Indexed(unique = true)
    private String userName;
    @NonNull
    private String passWord;
    @Field("journalEntryIds")
    @JsonSerialize(contentUsing = ObjectIdSerializer.class)
    private List<ObjectId> journalEntryIds = new ArrayList<>();




    public ObjectId getId() {
        return id;
    }

    public List<ObjectId> getJournalEntryIds() {
        return journalEntryIds;
    }

    public void setJournalEntryIds(List<ObjectId> journalEntryIds) {
        this.journalEntryIds = journalEntryIds;
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


}
