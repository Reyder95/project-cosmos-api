package com.projectcosmos.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.projectcosmos.api.repository.OAuthAccountRepository;
import com.projectcosmos.api.config.Helpers;
import com.projectcosmos.api.dto.OAuthAccount.OAuthAccountUpdateDto;
import com.projectcosmos.api.entity.OAuthAccount;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/oauth")
@RequiredArgsConstructor
public class OAuthAccountController {
    private final OAuthAccountRepository oauthAccountRepository;
    private final Helpers helpers;
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllOAuthAccounts() {
        List<OAuthAccount> accounts = oauthAccountRepository.findAll();
        return helpers.createResponseEntity(true, accounts, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getOAuthAccountById(@PathVariable Integer id) {
        OAuthAccount account = oauthAccountRepository.findById(id).orElse(null);
        if (account == null)
            return helpers.createResponseEntity(false, null, "OAuth account not found", HttpStatus.NOT_FOUND);

        return helpers.createResponseEntity(true, account, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createOAuthAccount(@RequestBody OAuthAccount account) {
        if (oauthAccountRepository.existsByProviderAndProviderUserId(account.getProvider(), account.getProviderUserId())) {
            return helpers.createResponseEntity(false, null, "OAuth account with the same provider and provider user ID already exists", HttpStatus.CONFLICT);
        }
        OAuthAccount savedAccount = oauthAccountRepository.save(account);
        return helpers.createResponseEntity(true, savedAccount, "OAuth account created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateOAuthAccount(@PathVariable Integer id, @RequestBody OAuthAccountUpdateDto updatedAccount) {
        OAuthAccount existing = oauthAccountRepository.findById(id).orElse(null);

        if (existing == null)
        {
            return helpers.createResponseEntity(false, null, "OAuth account not found", HttpStatus.NOT_FOUND);
        }

        if (updatedAccount.getProvider() != null) {
            existing.setProvider(updatedAccount.getProvider());
        }

        if (updatedAccount.getProviderUserId() != null) {
            if (!updatedAccount.getProviderUserId().equals(existing.getProviderUserId()) && 
                oauthAccountRepository.existsByProviderAndProviderUserId(
                    existing.getProvider(), updatedAccount.getProviderUserId())) {
                    return helpers.createResponseEntity(false, null, "Provider User ID already exists", HttpStatus.CONFLICT);
                }
            
            existing.setProviderUserId(updatedAccount.getProviderUserId());
        }

        OAuthAccount savedAccount = oauthAccountRepository.save(existing);
        
        return helpers.createResponseEntity(true, savedAccount, "OAuth account updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteOAuthAccount(@PathVariable Integer id) {
        if (!oauthAccountRepository.existsById(id)) {
            return helpers.createResponseEntity(false, null, "OAuth account not found", HttpStatus.NOT_FOUND);
        }

        oauthAccountRepository.deleteById(id);
        return helpers.createResponseEntity(true, null, "OAuth account deleted successfully", HttpStatus.OK);
    }
}

