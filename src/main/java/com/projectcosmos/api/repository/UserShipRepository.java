package com.projectcosmos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.projectcosmos.api.entity.UserShip;

@RepositoryRestResource(collectionResourceRel = "userShips", path = "user-ships", exported = false)
public interface UserShipRepository extends JpaRepository<UserShip, Integer> {
    
}
