package org.pragadeesh.recipesharingplatform.repository;

import org.pragadeesh.recipesharingplatform.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, UUID> {

    List<Recipe> findByTitleContainingIgnoreCase(String title);
    List<Recipe> findByChefId(UUID chefId);
    List<Recipe> findByLabels_Name(String labelName);
    List<Recipe> findByPublicationDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}

