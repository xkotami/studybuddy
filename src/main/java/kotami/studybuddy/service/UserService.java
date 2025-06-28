package kotami.studybuddy.service;

import kotami.studybuddy.entity.User;
import kotami.studybuddy.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }


    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        User user = userOptional.get();

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole())))
                .build();
    }

    @Transactional
    public User updateUser(Long id, User updated) {
        User user = getUserById(id);
        user.setUsername(updated.getUsername());
        user.setPassword(updated.getPassword());
        user.setEmail(updated.getEmail());
        user.setStreak(updated.getStreak());
        // Note: if you want to replace buddies, you can manage that here
        return user;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}