package org.pragadeesh.recipesharingplatform.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Recipe {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Lob
    private String ingredients;

    @Lob
    private String steps;

    private LocalDateTime publicationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chefId", nullable = false)
    private User chef;

    private String imageUrl;

    @ManyToMany
    @JoinTable(
            name = "recipe_labels",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private List<Label> labels;

    private int likeCount;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
