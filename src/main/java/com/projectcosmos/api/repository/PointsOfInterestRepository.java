package com.projectcosmos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.projectcosmos.api.entity.PointsOfInterest;

@RepositoryRestResource(collectionResourceRel = "pointsOfInterest", path = "points-of-interest", exported = false)
public interface PointsOfInterestRepository extends JpaRepository<PointsOfInterest, Integer> {
    
}
