package kotami.studybuddy.controller;

import io.swagger.v3.oas.annotations.Operation;
import kotami.studybuddy.dto.UserDto;
import kotami.studybuddy.entity.User;
import kotami.studybuddy.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Get users")
    public List<UserDto> getUsers() {
        return userService.getAllUsers().stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getStreak()
                )).collect(Collectors.toList());
    }
}
