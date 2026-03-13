package com.projectcosmos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.projectcosmos.api.entity.PoiMiningResource;

@RepositoryRestResource(collectionResourceRel = "poiMiningResources", path = "poi-mining-resources", exported = false)
public interface PoiMiningResourceRepository extends JpaRepository<PoiMiningResource, Integer> {
    
}
