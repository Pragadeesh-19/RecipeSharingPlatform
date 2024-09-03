package org.pragadeesh.recipesharingplatform.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class RecipeDto {

    private UUID id;
    private String title;
    private String ingredients;
    private String steps;
    private String imageUrl;
    private List<String> labels;
    private int likeCount;
}
