package com.jhaempyre.journalApp.service;

import com.jhaempyre.journalApp.entity.JournalEntry;
import com.jhaempyre.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@Service
@Component
public class JournalEntryService {

	@Autowired
	private JournalEntryRepository journalEntryRepository;

	public void saveEntry(JournalEntry journalEntry) {
		journalEntry.setDate(LocalDateTime.now());
		journalEntryRepository.save(journalEntry);
	}

	public List<JournalEntry> getAllEntry() {
		return journalEntryRepository.findAll();
	}

	public Optional<JournalEntry> findById(ObjectId Id) {
		return journalEntryRepository.findById(Id);
	}

	public void deleteById(ObjectId Id) {
		journalEntryRepository.deleteById(Id);
	}

	public JournalEntry updateEntry(ObjectId id, String title, String content) {
		Optional<JournalEntry> optionalEntry = journalEntryRepository.findById(id);

		if (optionalEntry.isPresent()) {
			JournalEntry journalEntry = optionalEntry.get();
			journalEntry.setTitle(title);
			journalEntry.setContent(content);
			return journalEntryRepository.save(journalEntry);  // Save updated entry
		} else {
			throw new RuntimeException("Journal entry not found for id: " + id);
		}
	}
}