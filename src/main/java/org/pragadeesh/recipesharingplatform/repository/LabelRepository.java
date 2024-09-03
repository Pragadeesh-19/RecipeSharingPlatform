package org.pragadeesh.recipesharingplatform.repository;

import org.pragadeesh.recipesharingplatform.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LabelRepository extends JpaRepository<Label, UUID> {

    Label findByName(String Name);
}
