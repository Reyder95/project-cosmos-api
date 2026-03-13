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
import com.projectcosmos.api.entity.ShipType;
import com.projectcosmos.api.repository.ShipTypeRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ship-types")
@RequiredArgsConstructor
public class ShipTypeController {
    
    private final ShipTypeRepository shipTypeRepository;
    private final Helpers helpers;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllShipTypes() {
        List<ShipType> shipTypes = shipTypeRepository.findAll();
        return helpers.createResponseEntity(true, shipTypes, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getShipTypeById(@PathVariable Integer id) {
        ShipType shipType = shipTypeRepository.findById(id).orElse(null);

        if (shipType == null) {
            return helpers.createResponseEntity(false, null, "Ship type not found", HttpStatus.NOT_FOUND);
        }

        return helpers.createResponseEntity(true, shipType, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createShipType(@RequestBody ShipType shipType) {
        ShipType savedShipType = shipTypeRepository.save(shipType);
        return helpers.createResponseEntity(true, savedShipType, null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateShipType(@PathVariable Integer id, @RequestBody ShipType shipType) {
        ShipType existingShipType = shipTypeRepository.findById(id).orElse(null);

        if (existingShipType == null) {
            return helpers.createResponseEntity(false, null, "Ship type not found", HttpStatus.NOT_FOUND);
        }

        if (shipType.getType() != null) {
            existingShipType.setType(shipType.getType());
        }

        ShipType updatedShipType = shipTypeRepository.save(existingShipType);

        return helpers.createResponseEntity(true, updatedShipType, null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteShipType(@PathVariable Integer id) {
        ShipType shipType = shipTypeRepository.findById(id).orElse(null);

        if (shipType == null) {
            return helpers.createResponseEntity(false, null, "Ship type not found", HttpStatus.NOT_FOUND);
        }

        shipTypeRepository.deleteById(id);
        return helpers.createResponseEntity(true, shipType, "Ship type with ID " + id + " deleted successfully", HttpStatus.OK);
    }
}
