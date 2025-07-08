package kotami.studybuddy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Entity
public class Buddy {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Setter
    @OneToMany(mappedBy = "buddy",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private List<Item> items = new ArrayList<>();

    // custom dateAdded calculator
    @Getter
    @Setter
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;

    // No-arg constructor required by JPA
    public Buddy() {
    }

    // Convenience constructor
    public Buddy(String name, Date dateAdded) {
        this.name = name;
        this.dateAdded = dateAdded;
    }

    // Getters and setters

    public int getAge() {
        return (int) ((new Date().getTime() - dateAdded.getTime()) / (1000 * 60 * 60 * 24));
    }

    // add item to list
    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Long id) {
        getItems().removeIf(item -> item.getId().equals(id));
    }
}