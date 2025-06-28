package kotami.studybuddy.controller;

import kotami.studybuddy.entity.Buddy;
import kotami.studybuddy.service.BuddyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
