package kotami.studybuddy.service;

import kotami.studybuddy.entity.Item;
import kotami.studybuddy.repository.ItemRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id " + id));
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    @Transactional
    public Item updateItem(Long id, Item updated) {
        Item item = getItemById(id);
        item.setName(updated.getName());
        item.setCategory(updated.getCategory());
        item.setDescription(updated.getDescription());
        item.setPrice(updated.getPrice());
        item.setDateAdded(updated.getDateAdded());
        // buddy relationship can be adjusted separately
        return item;
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}