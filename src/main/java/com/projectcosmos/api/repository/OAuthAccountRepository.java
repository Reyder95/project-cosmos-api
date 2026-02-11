package com.projectcosmos.api.repository;

import com.projectcosmos.api.entity.OAuthAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "oauthAccounts", path = "oauth", exported = false)
public interface OAuthAccountRepository extends JpaRepository<OAuthAccount, Integer> 
{
    boolean existsByProviderAndProviderUserId(String provider, String providerUserId);
}
