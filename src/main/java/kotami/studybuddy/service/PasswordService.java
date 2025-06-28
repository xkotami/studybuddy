package kotami.studybuddy.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordService {
    private final PasswordEncoder encoder = new BCryptPasswordEncoder(12);
    // ↑“12” is the strength/work-factor; you can tune this.

    public String hash(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public boolean matches(String rawPassword, String hashed) {
        return encoder.matches(rawPassword, hashed);
    }
}
