package kotami.studybuddy.dto;

public record UserDto (
        Long id,
        String username,
        String email,
        int streak
) {}
