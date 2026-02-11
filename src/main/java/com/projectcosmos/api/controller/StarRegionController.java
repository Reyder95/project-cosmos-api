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
import com.projectcosmos.api.entity.StarRegion;
import com.projectcosmos.api.repository.StarRegionRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/star-regions")
@RequiredArgsConstructor
public class StarRegionController {
    private final StarRegionRepository starRegionRepository;
    private final Helpers helpers;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStarRegions() {
        List<StarRegion> starRegions = starRegionRepository.findAll();

        return helpers.createResponseEntity(true, starRegions, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getStarRegionById(@PathVariable Integer id) {
        StarRegion starRegion = starRegionRepository.findById(id).orElse(null);

        if (starRegion == null) {
            return helpers.createResponseEntity(false, null, "Star region not found", HttpStatus.NOT_FOUND);
        }

        return helpers.createResponseEntity(true, starRegion, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createStarRegion(@RequestBody StarRegion newStarRegion) {
        if (newStarRegion.getRegionName() == null || newStarRegion.getRegionName().isBlank()) {
            return helpers.createResponseEntity(false, null, "Region name is required! (regionName)", HttpStatus.BAD_REQUEST);
        }

        StarRegion savedStarRegion = starRegionRepository.save(newStarRegion);
        return helpers.createResponseEntity(true, savedStarRegion, "Star region created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStarRegion(@PathVariable Integer id, @RequestBody StarRegion updatedStarRegion) {
         StarRegion starRegion = starRegionRepository.findById(id).orElse(null);

        if (starRegion == null) {
            return helpers.createResponseEntity(false, null, "Star region not found", HttpStatus.NOT_FOUND);
        }

        if (updatedStarRegion.getRegionName() == null || updatedStarRegion.getRegionName().isBlank()) {
            return helpers.createResponseEntity(false, null, "Region name is required! (regionName)", HttpStatus.BAD_REQUEST);
        }

        starRegion.setRegionName(updatedStarRegion.getRegionName());
        StarRegion savedStarRegion = starRegionRepository.save(starRegion);
        return helpers.createResponseEntity(true, savedStarRegion, "Star region updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteStarRegion(@PathVariable Integer id) {
        StarRegion starRegion = starRegionRepository.findById(id).orElse(null);

        if (starRegion == null) {
            return helpers.createResponseEntity(false, null, "Star region not found", HttpStatus.NOT_FOUND);
        }

        starRegionRepository.deleteById(id);
        return helpers.createResponseEntity(true, starRegion, "Star region with ID " + id + " deleted successfully", HttpStatus.OK);
    }
}
