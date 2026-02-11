package com.projectcosmos.api.repository;

import com.projectcosmos.api.entity.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "stars", path = "stars", exported = false)
public interface StarRepository extends JpaRepository<Star, Integer> {
    
}
