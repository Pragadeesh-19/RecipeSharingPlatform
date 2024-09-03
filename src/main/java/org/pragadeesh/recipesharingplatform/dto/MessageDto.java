package org.pragadeesh.recipesharingplatform.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MessageDto {

    private UUID senderId;
    private UUID receiverId;
    private String content;
}
