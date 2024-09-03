package org.pragadeesh.recipesharingplatform.dto;

import lombok.Data;
import org.pragadeesh.recipesharingplatform.model.Role;

@Data
public class UserDto {

    private String username;
    private String email;
    private String password;
    private Role role;
}
