package com.projectcosmos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.projectcosmos.api.entity.UserShipMod;

@RepositoryRestResource(collectionResourceRel = "userShipMods", path = "user-ship-mods", exported = false)
public interface UserShipModRepository extends JpaRepository<UserShipMod, Integer> {
    
}
