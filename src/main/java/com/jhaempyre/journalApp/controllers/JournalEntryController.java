package com.jhaempyre.journalApp.controllers;


import com.jhaempyre.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long, JournalEntry> journalEntries = new HashMap<>();
@GetMapping("/getAll")
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }
@PostMapping("/postSome")
    public boolean CreateEntry(@RequestBody JournalEntry entry){
        journalEntries.put(entry.getId(),entry);
        System.out.println(journalEntries);
        return true;
    }
@GetMapping("/getById/{Id}")
    public JournalEntry getById(@PathVariable long Id){
        return journalEntries.get(Id);
    }
@DeleteMapping("/delete/{Id}")
    public JournalEntry deleteById(@PathVariable long Id){
        return journalEntries.remove(Id);
}
@PutMapping("/putIt/{Id}")
    public boolean putById(@PathVariable long Id , @RequestBody JournalEntry entry){
    journalEntries.put(Id,entry);
    return true;
}
}
