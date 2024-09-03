package org.pragadeesh.recipesharingplatform.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Recipe {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    @Lob
    private String ingredients;

    @Lob
    private String steps;

    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "chef_id", nullable = false)
    private User chef;

    private String imageUrl;

    @ManyToMany
    @JoinTable(
            name = "recipe_label",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private List<Label> labels;
}
