package com.projectcosmos.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.projectcosmos.api.entity.StarSystem;

@RepositoryRestResource(collectionResourceRel = "starSystems", path = "star-systems", exported = false)
public interface StarSystemRepository extends JpaRepository<StarSystem, Integer> {
    List<StarSystem> findByGalaxy(String galaxy);
}
