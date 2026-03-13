package com.projectcosmos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.projectcosmos.api.entity.Module;

@RepositoryRestResource(collectionResourceRel = "modules", path = "modules", exported = false)
public interface ModuleRepository extends JpaRepository<Module, Integer> {
    
}
