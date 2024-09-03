package org.pragadeesh.recipesharingplatform.dto;

import lombok.Data;
import org.pragadeesh.recipesharingplatform.model.Role;

import java.util.List;
import java.util.UUID;

@Data
public class UserDto {

    private UUID id;
    private String username;
    private String email;
    private String password;
    private Role role;
    private List<RecipeDto> recipes;
}
