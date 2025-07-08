package kotami.studybuddy.repository;

import jakarta.annotation.PostConstruct;
import kotami.studybuddy.entity.Buddy;
import kotami.studybuddy.entity.Item;
import kotami.studybuddy.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class DbInitializer {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final BuddyRepository buddyRepository;
    private final PasswordEncoder passwordEncoder;


    public DbInitializer(UserRepository userRepository, ItemRepository itemRepository, BuddyRepository buddyRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.buddyRepository = buddyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() throws Exception {
        // clear db
        userRepository.deleteAll();
        itemRepository.deleteAll();
        buddyRepository.deleteAll();
        // 1st user with two buddies
        User alice = new User("alice", passwordEncoder.encode("password123"), "alice@example.com", 0, new ArrayList<>(), "USER");

        Buddy buddyBob = new Buddy();
        buddyBob.setName("Bob");
        buddyBob.setDateAdded(new Date());
        alice.addBuddy(buddyBob);

        Item book = new Item();
        book.setName("Java Programming book");
        book.setCategory("Books");
        book.setDescription("A comprehensive guide to Java");
        book.setDateAdded(new Date());
        book.setPrice(20);
        buddyBob.getItems().add(book);
        book.setBuddy(buddyBob);

        Buddy buddyCarol = new Buddy();
        buddyCarol.setName("Carol");
        buddyCarol.setDateAdded(new Date());
        alice.addBuddy(buddyCarol);

        Item notebook = new Item();
        notebook.setName("Macbook");
        notebook.setCategory("Electronics");
        notebook.setDescription("A fancy laptop");
        notebook.setDateAdded(new Date());
        notebook.setPrice(1100);
        buddyCarol.getItems().add(notebook);
        notebook.setBuddy(buddyCarol);

        // 2nd user with one buddy
        User dave = new User("dave", "secret!", "dave@example.com", 0, new ArrayList<>(), "ADMIN");

        Buddy buddyEve = new Buddy();
        buddyEve.setName("Eve");
        buddyEve.setDateAdded(new Date());
        dave.addBuddy(buddyEve);

        Item pen = new Item();
        pen.setName("Fountain Pen");
        pen.setCategory("Stationery");
        pen.setDescription("Smooth-writing fountain pen");
        pen.setDateAdded(new Date());
        pen.setPrice(10);
        buddyEve.getItems().add(pen);
        pen.setBuddy(buddyEve);

        // Persist both users (cascades will save buddies and items)
        userRepository.save(alice);
        userRepository.save(dave);
    }
}