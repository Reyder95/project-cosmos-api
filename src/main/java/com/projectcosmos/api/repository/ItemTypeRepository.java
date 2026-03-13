package com.projectcosmos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.projectcosmos.api.entity.ItemType;

@RepositoryRestResource(collectionResourceRel = "itemTypes", path = "item-types", exported = false)
public interface ItemTypeRepository extends JpaRepository<ItemType, Integer> {
    
}
