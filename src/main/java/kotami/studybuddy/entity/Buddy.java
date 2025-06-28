package kotami.studybuddy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private List<Item> items = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date age;

    // No-arg constructor required by JPA
    public Buddy() {
    }

    // Convenience constructor
    public Buddy(String name, Date age) {
        this.name = name;
        this.age = age;
    }

    // Getters and setters

}