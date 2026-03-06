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
import com.projectcosmos.api.entity.Ship;
import com.projectcosmos.api.repository.ShipRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ships")
@RequiredArgsConstructor
public class ShipController {
    private final ShipRepository shipRepository;
    private final Helpers helpers;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllShips() {
        List<Ship> ships = shipRepository.findAll();

        return helpers.createResponseEntity(true, ships, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getShipById(@PathVariable Integer id) {
        Ship ship = shipRepository.findById(id).orElse(null);

        if (ship == null) {
            return helpers.createResponseEntity(false, null, "Ship not found", HttpStatus.NOT_FOUND);
        }

        return helpers.createResponseEntity(true, ship, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createShip(@RequestBody Ship ship) {
        
        Ship savedShip = shipRepository.save(ship);

        return helpers.createResponseEntity(true, savedShip, null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateShip(@PathVariable Integer id, @RequestBody Ship ship) {
        Ship existingShip = shipRepository.findById(id).orElse(null);

        if (existingShip == null) {
            return helpers.createResponseEntity(false, null, "Ship not found", HttpStatus.NOT_FOUND);
        }

        if (ship.getShipName() != null) {
            existingShip.setShipName(ship.getShipName());
        }

        if (ship.getShipPicIdentifier() != null) {
            existingShip.setShipPicIdentifier(ship.getShipPicIdentifier());
        }

        if (ship.getShipTypeId() != null) {
            existingShip.setShipTypeId(ship.getShipTypeId());
        }

        if (ship.getHighSlots() != null) {
            existingShip.setHighSlots(ship.getHighSlots());
        }

        if (ship.getMediumSlots() != null) {
            existingShip.setMediumSlots(ship.getMediumSlots());
        }

        if (ship.getLowSlots() != null) {
            existingShip.setLowSlots(ship.getLowSlots());
        }

        Ship updatedShip = shipRepository.save(existingShip);

        return helpers.createResponseEntity(true, updatedShip, null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteShip(@PathVariable Integer id) {
        Ship ship = shipRepository.findById(id).orElse(null);

        if (ship == null) {
            return helpers.createResponseEntity(false, null, "Ship not found", HttpStatus.NOT_FOUND);
        }

        shipRepository.deleteById(id);

        return helpers.createResponseEntity(true, null, "Ship with ID " + id + " deleted successfully", HttpStatus.OK);
    }
}
