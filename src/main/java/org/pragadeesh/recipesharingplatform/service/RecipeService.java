package org.pragadeesh.recipesharingplatform.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.pragadeesh.recipesharingplatform.dto.RecipeDto;
import org.pragadeesh.recipesharingplatform.model.Label;
import org.pragadeesh.recipesharingplatform.model.Recipe;
import org.pragadeesh.recipesharingplatform.model.User;
import org.pragadeesh.recipesharingplatform.repository.LabelRepository;
import org.pragadeesh.recipesharingplatform.repository.RecipeRepository;
import org.pragadeesh.recipesharingplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final LabelRepository labelRepository;
    private final UserRepository userRepository;
    @Autowired
    public RecipeService(RecipeRepository recipeRepository, LabelRepository labelRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.labelRepository = labelRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public RecipeDto createRecipe(RecipeDto recipeDto, UUID chefId) {

        User chef = userRepository.findById(chefId)
                .orElseThrow(() -> new EntityNotFoundException("Chef not found with id " + chefId));

        Recipe recipe = new Recipe();
        recipe.setTitle(recipe.getTitle());
        recipe.setIngredients(recipe.getIngredients());
        recipe.setSteps(recipeDto.getSteps());
        recipe.setChef(chef);
        recipe.setImageUrl(recipe.getImageUrl());
        recipe.setPublicationDate(LocalDateTime.now());

        List<Label> labels = recipe.getLabels().stream()
                .map(labelName -> labelRepository.findByName(String.valueOf(labelName))
                        .orElseGet(() -> {
                            Label label = new Label();
                            label.setName(String.valueOf(labelName));
                            return labelRepository.save(label);
                        }))
                .collect(Collectors.toList());

        recipe.setLabels(labels);

        Recipe saveRecipe = recipeRepository.save(recipe);
        return convertToDto(saveRecipe);
    }

    public List<RecipeDto> searchRecipes(String keyword, String labelName) {
        List<Recipe> recipes;

        if(keyword != null && labelName != null) {
            recipes = recipeRepository.findByTitleContainingIgnoreCase(keyword).stream()
                    .filter(recipe -> recipe.getLabels().stream()
                            .anyMatch(label -> label.getName().equalsIgnoreCase(labelName)))
                    .collect(Collectors.toList());
        } else if(keyword != null) {
            recipes = recipeRepository.findByTitleContainingIgnoreCase(keyword);
        } else if (labelName != null) {
            recipes = recipeRepository.findByLabels_Name(labelName);
        } else {
            recipes = recipeRepository.findAll();
        }

        return recipes.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public RecipeDto findRecipeById(UUID id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recipe not found"));
        return convertToDto(recipe);
    }

    public void likeRecipe(UUID recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new EntityNotFoundException("Recipe not found"));
        recipe.setLikeCount(recipe.getLikeCount() + 1);
        recipeRepository.save(recipe);
    }

    public RecipeDto convertToDto(Recipe recipe) {
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(recipe.getId());
        recipeDto.setTitle(recipe.getTitle());
        recipeDto.setIngredients(recipe.getIngredients());
        recipeDto.setSteps(recipe.getSteps());
        recipeDto.setImageUrl(recipe.getImageUrl());
        recipeDto.setLabels(recipe.getLabels().stream()
                .map(Label::getName)
                .collect(Collectors.toList()));
        recipeDto.setLikeCount(recipe.getLikeCount());
        return recipeDto;
    }
}
