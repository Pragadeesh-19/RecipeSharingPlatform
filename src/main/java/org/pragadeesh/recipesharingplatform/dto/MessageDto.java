package org.pragadeesh.recipesharingplatform.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MessageDto {

    private UUID id;
    private String content;
    private UUID senderId;
    private UUID receiverId;
    private String senderUsername;
    private String receiverUsername;
}
