package kotami.studybuddy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    private String category;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buddy_id", nullable = true)
    @JsonBackReference
    private Buddy buddy;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date dateAdded;

    private Date dateBought;

    @NotNull
    private Integer price;

    // No-argument constructor required by JPA
    public Item() {
    }

    // Convenience constructor (without id)
    public Item(String name,
                String category,
                String description,
                Date dateAdded,
                Date dateBought,
                Integer price,
                Buddy buddy) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.dateAdded = dateAdded;
        this.dateBought = dateBought;
        this.price = price;
        this.buddy = buddy;
    }

    // Full-argument constructor
    public Item(Long id,
                String name,
                String category,
                String description,
                Date dateAdded,
                Date dateBought,
                Integer price,
                Buddy buddy) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.dateAdded = dateAdded;
        this.dateBought = dateBought;
        this.price = price;
        this.buddy = buddy;
    }

    // Getters & setters

}