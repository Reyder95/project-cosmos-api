package com.projectcosmos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.projectcosmos.api.entity.ItemSubType;

@RepositoryRestResource(collectionResourceRel = "itemSubTypes", path = "item-sub-types", exported = false)
public interface ItemSubTypeRepository extends JpaRepository<ItemSubType, Integer> {
    
}
