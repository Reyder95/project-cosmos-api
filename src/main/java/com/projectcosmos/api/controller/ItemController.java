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
import com.projectcosmos.api.entity.Item;
import com.projectcosmos.api.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;
    private final Helpers helpers;
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllItems() {
        List<Item> items = itemRepository.findAll();

        return helpers.createResponseEntity(true, items, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getItemById(@PathVariable Integer id) {
        Item item = itemRepository.findById(id).orElse(null);

        if (item == null) {
            return helpers.createResponseEntity(false, null, "Item not found", HttpStatus.NOT_FOUND);
        }

        return helpers.createResponseEntity(true, item, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createItem(@RequestBody Item item) {
        
        Item savedItem = itemRepository.save(item);

        return helpers.createResponseEntity(true, savedItem, null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateItem(@PathVariable Integer id, @RequestBody Item item) {
        Item existingItem = itemRepository.findById(id).orElse(null);

        if (existingItem == null) {
            return helpers.createResponseEntity(false, null, "Item not found", HttpStatus.NOT_FOUND);
        }

        if (item.getShipId() != null) {
            existingItem.setShipId(item.getShipId());
        }

        if (item.getModuleId() != null) {
            existingItem.setModuleId(item.getModuleId());
        }

        if (item.getItemName() != null) {
            existingItem.setItemName(item.getItemName());
        }

        if (item.getDescription() != null) {
            existingItem.setDescription(item.getDescription());
        }

        if (item.getBasePrice() != null) {
            existingItem.setBasePrice(item.getBasePrice());
        }

        if (item.getItemTypeId() != null) {
            existingItem.setItemTypeId(item.getItemTypeId());
        }

        if (item.getItemSubTypeId() != null) {
            existingItem.setItemSubTypeId(item.getItemSubTypeId());
        }

        if (item.getItemIdentifier() != null) {
            existingItem.setItemIdentifier(item.getItemIdentifier());
        }

        Item updatedItem = itemRepository.save(existingItem);

        return helpers.createResponseEntity(true, updatedItem, null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteItem(@PathVariable Integer id) {
        Item existingItem = itemRepository.findById(id).orElse(null);

        if (existingItem == null) {
            return helpers.createResponseEntity(false, null, "Item not found", HttpStatus.NOT_FOUND);
        }

        itemRepository.delete(existingItem);

        return helpers.createResponseEntity(true, existingItem, "Item deleted successfully", HttpStatus.OK);
    }
}
