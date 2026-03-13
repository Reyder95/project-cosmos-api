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
import com.projectcosmos.api.entity.ItemSubType;
import com.projectcosmos.api.repository.ItemSubTypeRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/item-sub-types")
@RequiredArgsConstructor
public class ItemSubTypeController {
    
    private final ItemSubTypeRepository itemSubTypeRepository;
    private final Helpers helpers;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllItemSubTypes() {
        List<ItemSubType> itemSubTypes = itemSubTypeRepository.findAll();
        return helpers.createResponseEntity(true, itemSubTypes, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getItemSubTypeById(@PathVariable Integer id) {
        ItemSubType itemSubType = itemSubTypeRepository.findById(id).orElse(null);

        if (itemSubType == null) {
            return helpers.createResponseEntity(false, null, "Item sub-type not found", HttpStatus.NOT_FOUND);
        }

        return helpers.createResponseEntity(true, itemSubType, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createItemSubType(@RequestBody ItemSubType itemSubType) {
        ItemSubType savedItemSubType = itemSubTypeRepository.save(itemSubType);
        return helpers.createResponseEntity(true, savedItemSubType, null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateItemSubType(@PathVariable Integer id, @RequestBody ItemSubType itemSubType) {
        ItemSubType existingItemSubType = itemSubTypeRepository.findById(id).orElse(null);

        if (existingItemSubType == null) {
            return helpers.createResponseEntity(false, null, "Item sub-type not found", HttpStatus.NOT_FOUND);
        }

        if (itemSubType.getType() != null) {
            existingItemSubType.setType(itemSubType.getType());
        }

        ItemSubType updatedItemSubType = itemSubTypeRepository.save(existingItemSubType);

        return helpers.createResponseEntity(true, updatedItemSubType, null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteItemSubType(@PathVariable Integer id) {
        ItemSubType itemSubType = itemSubTypeRepository.findById(id).orElse(null);

        if (itemSubType == null) {
            return helpers.createResponseEntity(false, null, "Item sub-type not found", HttpStatus.NOT_FOUND);
        }

        itemSubTypeRepository.deleteById(id);
        return helpers.createResponseEntity(true, itemSubType, "Item sub-type with ID " + id + " deleted successfully", HttpStatus.OK);
    }
}
