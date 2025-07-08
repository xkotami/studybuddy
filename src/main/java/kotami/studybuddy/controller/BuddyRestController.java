package kotami.studybuddy.controller;

import kotami.studybuddy.entity.Buddy;
import kotami.studybuddy.service.BuddyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buddy")
public class BuddyRestController {
    private final BuddyService buddyService;

    public BuddyRestController(BuddyService buddyService) {
        this.buddyService = buddyService;
    }

    @GetMapping
    public List<Buddy> getBuddies() {
        return buddyService.getBuddies();
    }

    // needs fixing
    @PutMapping("/{itemId}/{buddyId}")
    public Buddy addItemToBuddy(@PathVariable Integer itemId, @PathVariable Integer buddyId) {
        Buddy result = buddyService.addItemToBuddyById(itemId, buddyId);
        return result;

    }

}
