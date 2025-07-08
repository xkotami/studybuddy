package kotami.studybuddy.service;

import kotami.studybuddy.entity.Buddy;
import kotami.studybuddy.entity.Item;
import kotami.studybuddy.repository.BuddyRepository;
import kotami.studybuddy.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuddyService {
    public final BuddyRepository buddyRepository;
    private final ItemRepository itemRepository;

    public BuddyService(BuddyRepository buddyRepository, ItemRepository itemRepository) {
        this.buddyRepository = buddyRepository;
        this.itemRepository = itemRepository;
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

    public Buddy addItemToBuddyById(Integer itemId, Integer buddyId) {
        try {
            // fetch item
            Item item = itemRepository.getItemById(Long.valueOf(itemId));
            Buddy buddy = buddyRepository.getBuddyById(Long.valueOf(buddyId));

            buddy.addItem(item);
            item.setBuddy(buddy);

            return buddyRepository.save(buddy);


        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    public void addItem(Item item, Buddy buddy) {
        buddy.getItems().add(item);
        buddyRepository.save(buddy);
    }

    public void removeItem(Item item, Buddy buddy) {
        buddy.getItems().remove(item);
        buddyRepository.save(buddy);
    }
}
