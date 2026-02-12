package com.projectcosmos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.projectcosmos.api.entity.Planet;

@RepositoryRestResource(collectionResourceRel = "planets", path = "planets", exported = false)
public interface PlanetRepository extends JpaRepository<Planet, Integer> {
    
}
