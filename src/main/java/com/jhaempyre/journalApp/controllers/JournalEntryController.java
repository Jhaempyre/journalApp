package com.jhaempyre.journalApp.controllers;

import com.jhaempyre.journalApp.entity.JournalEntry;
import com.jhaempyre.journalApp.entity.User;
import com.jhaempyre.journalApp.repository.UserEntryRepository;
import com.jhaempyre.journalApp.service.JournalEntryService;
import com.jhaempyre.journalApp.service.UserEntryService;
import jdk.jfr.Unsigned;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserEntryService userEntryService ;

    @Autowired
    private UserEntryRepository userEntryRepository ;

    @GetMapping("/{userName}/getAll")
    public ResponseEntity<?> getAllJournalByUser(@PathVariable String userName) {
        try {
            User user = userEntryRepository.findByUserName(userName).orElse(null);
            assert user != null;
            List<ObjectId> entries = user.getJournalEntryIds();
            if (!entries.isEmpty()) {
                return ResponseEntity.ok(entries);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No journal entries found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/{userName}/postSome")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry entry , @PathVariable String userName) {
        try {

            journalEntryService.saveEntry(entry,userName);
                        return ResponseEntity.status(HttpStatus.CREATED).body(entry);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<JournalEntry> getById(@PathVariable("id") ObjectId id) {
        try {
            return journalEntryService.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseThrow(() -> new RuntimeException("Journal Entry not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/{userName}/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") ObjectId id,@PathVariable String userName) {
        try {
            journalEntryService.deleteById(id,userName);
            return ResponseEntity.ok("Journal Entry deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/putIt/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable("id") ObjectId id, @RequestBody JournalEntry updatedEntry) {
        try {
            JournalEntry updated = journalEntryService.updateEntry(id, updatedEntry.getTitle(), updatedEntry.getContent());
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
