package com.projectcosmos.api.controller;

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

import com.projectcosmos.api.config.Helpers;
import com.projectcosmos.api.entity.UserInventory;
import com.projectcosmos.api.repository.UserInventoryRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user-inventories")
@RequiredArgsConstructor
public class UserInventoryController {
    private final UserInventoryRepository userInventoryRepository;
    private final Helpers helpers;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUserInventories() {
        List<UserInventory> userInventories = userInventoryRepository.findAll();

        return helpers.createResponseEntity(true, userInventories, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserInventoryById(@PathVariable Integer id) {
        UserInventory userInventory = userInventoryRepository.findById(id).orElse(null);

        if (userInventory == null) {
            return helpers.createResponseEntity(false, null, "User inventory not found", HttpStatus.NOT_FOUND);
        }

        return helpers.createResponseEntity(true, userInventory, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createUserInventory(@RequestBody UserInventory userInventory) {
        
        UserInventory savedUserInventory = userInventoryRepository.save(userInventory);

        return helpers.createResponseEntity(true, savedUserInventory, null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUserInventory(@PathVariable Integer id, @RequestBody UserInventory userInventory) {
        UserInventory existingUserInventory = userInventoryRepository.findById(id).orElse(null);

        if (existingUserInventory == null) {
            return helpers.createResponseEntity(false, null, "User inventory not found", HttpStatus.NOT_FOUND);
        }

        if (userInventory.getUserId() != null) {
            existingUserInventory.setUserId(userInventory.getUserId());
        }

        if (userInventory.getQuantity() != null) {
            existingUserInventory.setQuantity(userInventory.getQuantity());
        }

        if (userInventory.getItemId() != null) {
            existingUserInventory.setItemId(userInventory.getItemId());
        }

        UserInventory updatedUserInventory = userInventoryRepository.save(existingUserInventory);

        return helpers.createResponseEntity(true, updatedUserInventory, null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUserInventory(@PathVariable Integer id) {
        UserInventory existingUserInventory = userInventoryRepository.findById(id).orElse(null);

        if (existingUserInventory == null) {
            return helpers.createResponseEntity(false, null, "User inventory not found", HttpStatus.NOT_FOUND);
        }

        userInventoryRepository.delete(existingUserInventory);

        return helpers.createResponseEntity(true, null, "User inventory deleted successfully", HttpStatus.OK);
    }
}
