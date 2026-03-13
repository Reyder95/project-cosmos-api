package com.projectcosmos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.projectcosmos.api.entity.StarPlanet;

@RepositoryRestResource(collectionResourceRel = "starPlanets", path = "star-planets", exported = false)
public interface StarPlanetRepository extends JpaRepository<StarPlanet, Integer> {
    
}
