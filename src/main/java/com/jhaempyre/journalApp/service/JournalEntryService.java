package com.jhaempyre.journalApp.service;

import com.jhaempyre.journalApp.entity.JournalEntry;
import com.jhaempyre.journalApp.entity.User;
import com.jhaempyre.journalApp.repository.JournalEntryRepository;
import com.jhaempyre.journalApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@Service
@Component
public class JournalEntryService {

	@Autowired
	private JournalEntryRepository journalEntryRepository;

	@Autowired
	private UserEntryRepository userEntryRepository;

	public JournalEntry saveEntry(JournalEntry journalEntry,String userName) {
		User user = userEntryRepository.findByUserName(userName).orElse(null);
		journalEntry.setDate(LocalDateTime.now());
		JournalEntry journal= journalEntryRepository.save(journalEntry);
		// Ensure the list is not null before adding the new entry
		if (user.getJournalEntryIds() == null) {
			user.setJournalEntryIds(new ArrayList<>());
		}

		user.getJournalEntryIds().add(journal.getId()); // Store only the ID
		userEntryRepository.save(user); // Save updated user with new journal entry ID

		return journal;
	}

	public List<JournalEntry> getAllEntry() {
		return journalEntryRepository.findAll();
	}

	public Optional<JournalEntry> findById(ObjectId Id) {
		return journalEntryRepository.findById(Id);
	}

	public void deleteById(ObjectId Id,String userName) {
		User user = userEntryRepository.findByUserName(userName).orElse(null);

		user.getJournalEntryIds().removeIf(objectId -> objectId.equals(Id));
		userEntryRepository.save(user);
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