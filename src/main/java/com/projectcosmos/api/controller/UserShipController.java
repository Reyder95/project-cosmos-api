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
import com.projectcosmos.api.entity.UserShip;
import com.projectcosmos.api.repository.UserShipRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user-ships")
@RequiredArgsConstructor
public class UserShipController {
    private final UserShipRepository userShipRepository;
    private final Helpers helpers;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUserShips() {
        List<UserShip> userShips = userShipRepository.findAll();

        return helpers.createResponseEntity(true, userShips, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserShipById(@PathVariable Integer id) {
        UserShip userShip = userShipRepository.findById(id).orElse(null);

        if (userShip == null) {
            return helpers.createResponseEntity(false, null, "User ship not found", HttpStatus.NOT_FOUND);
        }

        return helpers.createResponseEntity(true, userShip, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createUserShip(@RequestBody UserShip userShip) {
        
        UserShip savedUserShip = userShipRepository.save(userShip);

        return helpers.createResponseEntity(true, savedUserShip, null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUserShip(@PathVariable Integer id, @RequestBody UserShip userShip) {
        UserShip existingUserShip = userShipRepository.findById(id).orElse(null);

        if (existingUserShip == null) {
            return helpers.createResponseEntity(false, null, "User ship not found", HttpStatus.NOT_FOUND);
        }

        if (userShip.getUserId() != null) {
            existingUserShip.setUserId(userShip.getUserId());
        }

        if (userShip.getShipId() != null) {
            existingUserShip.setShipId(userShip.getShipId());
        }

        if (userShip.getShipName() != null) {
            existingUserShip.setShipName(userShip.getShipName());
        }

        UserShip updatedUserShip = userShipRepository.save(existingUserShip);

        return helpers.createResponseEntity(true, updatedUserShip, null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUserShip(@PathVariable Integer id) {
        UserShip existingUserShip = userShipRepository.findById(id).orElse(null);

        if (existingUserShip == null) {
            return helpers.createResponseEntity(false, null, "User ship not found", HttpStatus.NOT_FOUND);
        }

        userShipRepository.delete(existingUserShip);

        return helpers.createResponseEntity(true, null, "User ship deleted successfully", HttpStatus.OK);
    }
}
