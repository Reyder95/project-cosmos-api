package com.projectcosmos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.projectcosmos.api.entity.Ship;

@RepositoryRestResource(collectionResourceRel = "ships", path = "ships", exported = false)
public interface ShipRepository extends JpaRepository<Ship, Integer> {  
    
}
