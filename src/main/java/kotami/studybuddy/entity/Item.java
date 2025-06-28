package kotami.studybuddy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

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
    private Date dateAdded;

    private Date dateBought;
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

    public Buddy getBuddy() {
        return buddy;
    }
    public void setBuddy(Buddy buddy) {
        this.buddy = buddy;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateBought() {
        return dateBought;
    }

    public void setDateBought(Date dateBought) {
        this.dateBought = dateBought;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}