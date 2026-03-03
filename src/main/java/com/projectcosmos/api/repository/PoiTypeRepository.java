package com.projectcosmos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.projectcosmos.api.entity.PoiType;

@RepositoryRestResource(collectionResourceRel = "poiTypes", path="poi-types", exported = false)
public interface PoiTypeRepository extends JpaRepository<PoiType, Integer> {
    
}
