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
import com.projectcosmos.api.entity.ItemType;
import com.projectcosmos.api.repository.ItemTypeRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/item-types")
@RequiredArgsConstructor
public class ItemTypeController {
    
    private final ItemTypeRepository itemTypeRepository;
    private final Helpers helpers;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllItemTypes() {
        List<ItemType> itemTypes = itemTypeRepository.findAll();
        return helpers.createResponseEntity(true, itemTypes, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getItemTypeById(@PathVariable Integer id) {
        ItemType itemType = itemTypeRepository.findById(id).orElse(null);

        if (itemType == null) {
            return helpers.createResponseEntity(false, null, "Item type not found", HttpStatus.NOT_FOUND);
        }

        return helpers.createResponseEntity(true, itemType, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createItemType(@RequestBody ItemType itemType) {
        ItemType savedItemType = itemTypeRepository.save(itemType);
        return helpers.createResponseEntity(true, savedItemType, null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updatedItemType(@PathVariable Integer id, @RequestBody ItemType itemType) {
        ItemType existingItemType = itemTypeRepository.findById(id).orElse(null);

        if (existingItemType == null) {
            return helpers.createResponseEntity(false, null, "Item type not found", HttpStatus.NOT_FOUND);
        }

        if (itemType.getType() != null) {
            existingItemType.setType(itemType.getType());
        }

        ItemType updatedItemType = itemTypeRepository.save(existingItemType);

        return helpers.createResponseEntity(true, updatedItemType, null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteItemType(@PathVariable Integer id) {
        ItemType itemType = itemTypeRepository.findById(id).orElse(null);

        if (itemType == null) {
            return helpers.createResponseEntity(false, null, "Item type not found", HttpStatus.NOT_FOUND);
        }

        itemTypeRepository.deleteById(id);
        return helpers.createResponseEntity(true, itemType, "Item type with ID " + id + " deleted successfully", HttpStatus.OK);
    }
}
