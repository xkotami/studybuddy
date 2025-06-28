package kotami.studybuddy.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private int streak;
    private String role;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<Buddy> buddies = new ArrayList<>();

    // JPA requires a no-argument constructor
    public User() {
    }

    // Convenience constructor (without id)
    public User(String username, String password, String email, int streak, List<Buddy> buddies, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.streak = 0;
        this.buddies = buddies;
        this.role = role;
    }

    // Full-argument constructor
    public User(Long id, String username, String password, String email, int streak, List<Buddy> buddies) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.streak = streak;
        this.buddies = buddies;
    }

    // Getters & Setters

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Buddy> getBuddies() {
        return buddies;
    }
    public void setBuddies(List<Buddy> buddies) {
        this.buddies = buddies;
    }

    public Long getId() {
        return id;
    }

    public int getStreak() {
        return streak;
    }
    public void setStreak(int streak) {
        this.streak = streak;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addBuddy(Buddy buddy) {
        this.buddies.add(buddy);
        buddy.setUser(this);
    }

    // equals & hashCode based on business key (username + email)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        User user = (User) o;
        return Objects.equals(username, user.username) &&
               Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }

    // toString()

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}