package com.projectcosmos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.projectcosmos.api.entity.StarMoon;

@RepositoryRestResource(collectionResourceRel = "starMoons", path = "star-moons", exported = false)
public interface StarMoonRepository extends JpaRepository<StarMoon, Integer> {
    
}
