package com.projectcosmos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.projectcosmos.api.entity.ShipType;

@RepositoryRestResource(collectionResourceRel = "shipTypes", path = "ship-types", exported = false)
public interface ShipTypeRepository extends JpaRepository<ShipType, Integer> {}
