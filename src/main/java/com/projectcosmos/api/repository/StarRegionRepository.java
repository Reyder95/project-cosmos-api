package com.projectcosmos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.projectcosmos.api.entity.StarRegion;

@RepositoryRestResource(collectionResourceRel = "starRegions", path = "star-regions", exported = false)
public interface StarRegionRepository extends JpaRepository<StarRegion, Integer> {}
