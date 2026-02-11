package com.projectcosmos.api.repository;

import com.projectcosmos.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "users", path = "users", exported = false)
public interface UserRepository extends JpaRepository<User, Integer> {}
