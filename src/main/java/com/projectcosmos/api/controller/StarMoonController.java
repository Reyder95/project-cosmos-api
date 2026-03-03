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
import com.projectcosmos.api.entity.StarMoon;
import com.projectcosmos.api.repository.StarMoonRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/star-moons")
@RequiredArgsConstructor
public class StarMoonController {
    private final StarMoonRepository starMoonRepository;
    private final Helpers helpers;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStarMoons() {
        List<StarMoon> starMoons = starMoonRepository.findAll();

        return helpers.createResponseEntity(true, starMoons, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getStarMoonById(@PathVariable Integer id) {
        StarMoon starMoon = starMoonRepository.findById(id).orElse(null);

        if (starMoon == null) {
            return helpers.createResponseEntity(false, null, "Star moon not found!", HttpStatus.NOT_FOUND);
        }

        return helpers.createResponseEntity(true, starMoon, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createStarMoon(@RequestBody StarMoon starMoon) {
        StarMoon savedStarMoon = starMoonRepository.save(starMoon);

        return helpers.createResponseEntity(true, savedStarMoon, null, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStarSystem(@PathVariable Integer id, @RequestBody StarMoon starMoon) {
        StarMoon existingStarMoon = starMoonRepository.findById(id).orElse(null);

        if (existingStarMoon == null) {
            return helpers.createResponseEntity(false, null, "Star moon not found!", HttpStatus.NOT_FOUND);
        }

        if (starMoon.getParentPlanetId() != null)
            existingStarMoon.setParentPlanetId(starMoon.getParentPlanetId());

        if (starMoon.getMoonTypeId() != null) {
            existingStarMoon.setMoonTypeId(starMoon.getMoonTypeId());
        }

        existingStarMoon = starMoonRepository.findById(id).orElse(null);

        if (starMoon.getSize() != null)
            existingStarMoon.setSize(starMoon.getSize());

        if (starMoon.getAngle() != null)
            existingStarMoon.setAngle(starMoon.getAngle());

        if (starMoon.getRadius() != null)
            existingStarMoon.setRadius(starMoon.getRadius());

        starMoonRepository.saveAndFlush(existingStarMoon);

        entityManager.clear();

        StarMoon refreshedStarMoon = starMoonRepository.findById(id).orElse(null);

        return helpers.createResponseEntity(true, refreshedStarMoon, null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteStarMoon(@PathVariable Integer Id) {
        StarMoon starMoon = starMoonRepository.findById(Id).orElse(null);

        if (starMoon == null) {
            return helpers.createResponseEntity(false, null, "Star moon not found!", HttpStatus.NOT_FOUND);
        }

        starMoonRepository.deleteById(Id);
        return helpers.createResponseEntity(true, starMoon, "Star Moon with ID " + Id + " deleted successfully", HttpStatus.OK);
    }
}
