package com.jhaempyre.journalApp.controllers;

import com.jhaempyre.journalApp.entity.JournalEntry;
import com.jhaempyre.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService ;

    @GetMapping("/getAll")
    public List<JournalEntry> getAll(){
        return journalEntryService.getAllEntry();
    }
    @PostMapping("/postSome")
    public JournalEntry CreateEntry(@RequestBody JournalEntry entry){
        journalEntryService.saveEntry(entry);
        return entry;
    }
    @GetMapping("/getById/{Id}")
    public JournalEntry getById(@PathVariable ObjectId Id){
        return journalEntryService.findById(Id).orElse(null);
    }
    @DeleteMapping("/delete/{Id}")
    public boolean deleteById(@PathVariable ObjectId Id){
        journalEntryService.deleteById(Id);
        return true;
    }

    @PutMapping("/putIt/{id}")
    public JournalEntry updateEntry(@PathVariable("id") ObjectId id, @RequestBody JournalEntry updatedEntry) {
        return journalEntryService.updateEntry(id, updatedEntry.getTitle(), updatedEntry.getContent());
    }


}
