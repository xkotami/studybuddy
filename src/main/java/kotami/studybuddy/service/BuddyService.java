package kotami.studybuddy.service;

import kotami.studybuddy.entity.Buddy;
import kotami.studybuddy.entity.Item;
import kotami.studybuddy.repository.BuddyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuddyService {
    public final BuddyRepository buddyRepository;

    public BuddyService(BuddyRepository buddyRepository) {
        this.buddyRepository = buddyRepository;
    }

    public List<Buddy> getBuddies() {
        return buddyRepository.findAll();
    }

    public Buddy getBuddyById(Long id) {
        return buddyRepository.findById(id).orElse(null);
    }

    public Buddy saveBuddy(Buddy buddy) {
        return buddyRepository.save(buddy);
    }

    public void deleteBuddy(Buddy buddy) {
        buddyRepository.delete(buddy);
    }

    public void deleteBuddyById(Long id) {
        buddyRepository.deleteById(id);
    }

    public void addItem(Item item, Buddy buddy) {
        buddy.getItems().add(item);
    }

    public void removeItem(Item item, Buddy buddy) {
        buddy.getItems().remove(item);
    }
}
