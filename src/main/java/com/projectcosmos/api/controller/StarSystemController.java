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
import com.projectcosmos.api.entity.StarSystem;
import com.projectcosmos.api.repository.StarSystemRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/star-systems")
@RequiredArgsConstructor
public class StarSystemController {
    private final StarSystemRepository starSystemRepository;
    private final Helpers helpers;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStarSystems() {
        List<StarSystem> starSystem = starSystemRepository.findAll();

        return helpers.createResponseEntity(true, starSystem, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getStarSystemById(@PathVariable Integer id) {
        StarSystem starSystem = starSystemRepository.findById(id).orElse(null);

        if (starSystem == null) {
            return helpers.createResponseEntity(false, null, "Star system not found", HttpStatus.NOT_FOUND);
        }

        return helpers.createResponseEntity(true, starSystem, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createStarSystem(@RequestBody StarSystem starSystem) {
        
        StarSystem savedStarSystem = starSystemRepository.save(starSystem);

        return helpers.createResponseEntity(true, savedStarSystem, null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStarSystem(@PathVariable Integer id, @RequestBody StarSystem starSystem) {
        StarSystem existingStarSystem = starSystemRepository.findById(id).orElse(null);

        if (existingStarSystem == null) {
            return helpers.createResponseEntity(false, null, "Star system not found", HttpStatus.NOT_FOUND);
        }

        if (starSystem.getSystemName() != null)
            existingStarSystem.setSystemName(starSystem.getSystemName());

        if (starSystem.getSecurityLevel() != null)
            existingStarSystem.setSecurityLevel(starSystem.getSecurityLevel());

        if (starSystem.getHeat() != null)
            existingStarSystem.setHeat(starSystem.getHeat());

        if (starSystem.getStarTypeId() != null)
            existingStarSystem.setStarTypeId(starSystem.getStarTypeId());

        if (starSystem.getRegionId() != null)
            existingStarSystem.setRegionId(starSystem.getRegionId());

        StarSystem updatedStarSystem = starSystemRepository.save(existingStarSystem);

        return helpers.createResponseEntity(true, updatedStarSystem, null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteStarSystem(@PathVariable Integer id) {
        StarSystem starSystem = starSystemRepository.findById(id).orElse(null);

        if (starSystem == null) {
            return helpers.createResponseEntity(false, null, "Star system not found", HttpStatus.NOT_FOUND);
        }

        starSystemRepository.deleteById(id);
        return helpers.createResponseEntity(true, starSystem, "Star system with ID " + id + " deleted successfully", HttpStatus.OK);
    }
}
