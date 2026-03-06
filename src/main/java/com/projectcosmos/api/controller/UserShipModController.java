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
import com.projectcosmos.api.entity.UserShipMod;
import com.projectcosmos.api.repository.UserShipModRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user-ship-mods")
@RequiredArgsConstructor
public class UserShipModController {
    private final UserShipModRepository userShipRepository;
    private final Helpers helpers;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUserShipMods() {
        List<UserShipMod> userShipMods = userShipRepository.findAll();

        return helpers.createResponseEntity(true, userShipMods, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserShipModById(@PathVariable Integer id) {
        UserShipMod userShipMod = userShipRepository.findById(id).orElse(null);

        if (userShipMod == null) {
            return helpers.createResponseEntity(false, null, "User ship mod not found", HttpStatus.NOT_FOUND);
        }

        return helpers.createResponseEntity(true, userShipMod, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createUserShipMod(@RequestBody UserShipMod userShipMod) {
        
        UserShipMod savedUserShipMod = userShipRepository.save(userShipMod);

        return helpers.createResponseEntity(true, savedUserShipMod, null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUserShipMod(@PathVariable Integer id, @RequestBody UserShipMod userShipMod) {
        UserShipMod existingUserShipMod = userShipRepository.findById(id).orElse(null);

        if (existingUserShipMod == null) {
            return helpers.createResponseEntity(false, null, "User ship mod not found", HttpStatus.NOT_FOUND);
        }

        if (userShipMod.getUserShipsId() != null) {
            existingUserShipMod.setUserShipsId(userShipMod.getUserShipsId());
        }

        if (userShipMod.getModuleId() != null) {
            existingUserShipMod.setModuleId(userShipMod.getModuleId());
        }

        if (userShipMod.getSlotType() != null) {
            existingUserShipMod.setSlotType(userShipMod.getSlotType());
        }

        UserShipMod updatedUserShipMod = userShipRepository.save(existingUserShipMod);

        return helpers.createResponseEntity(true, updatedUserShipMod, null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUserShipMod(@PathVariable Integer id) {
        UserShipMod existingUserShipMod = userShipRepository.findById(id).orElse(null);

        if (existingUserShipMod == null) {
            return helpers.createResponseEntity(false, null, "User ship mod not found", HttpStatus.NOT_FOUND);
        }

        userShipRepository.delete(existingUserShipMod);

        return helpers.createResponseEntity(true, null, "User ship mod deleted successfully", HttpStatus.OK);
    }
}
