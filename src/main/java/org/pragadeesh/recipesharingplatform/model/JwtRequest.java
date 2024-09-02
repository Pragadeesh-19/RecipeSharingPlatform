package org.pragadeesh.recipesharingplatform.model;

import lombok.Data;

@Data
public class JwtRequest {

    private String username;
    private String password;
}
