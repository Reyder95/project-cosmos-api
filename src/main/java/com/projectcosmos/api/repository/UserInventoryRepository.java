package com.projectcosmos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.projectcosmos.api.entity.UserInventory;

@RepositoryRestResource(collectionResourceRel = "userInventories", path = "user-inventories", exported = false)
public interface UserInventoryRepository extends JpaRepository<UserInventory, Integer> {
    
}
