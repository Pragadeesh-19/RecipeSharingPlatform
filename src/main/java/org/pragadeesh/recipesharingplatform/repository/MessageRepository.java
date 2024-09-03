package org.pragadeesh.recipesharingplatform.repository;

import org.pragadeesh.recipesharingplatform.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

    List<Message> findBySenderIdAndReceiverId(UUID senderId, UUID receiverId);
    List<Message> findByReceiverId(UUID receiverId);
}
