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
import com.projectcosmos.api.entity.StarPlanet;
import com.projectcosmos.api.repository.StarPlanetRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/star-planets")
@RequiredArgsConstructor
public class StarPlanetController {
    private final StarPlanetRepository starPlanetRepository;
    private final Helpers helpers;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStarPlanets() {
        List<StarPlanet> starPlanet = starPlanetRepository.findAll();

        return helpers.createResponseEntity(true, starPlanet, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getStarSystemById(@PathVariable Integer id) {
        StarPlanet starPlanet = starPlanetRepository.findById(id).orElse(null);

        if (starPlanet == null) {
            return helpers.createResponseEntity(false, null, "Star Planet not found!", HttpStatus.NOT_FOUND);
        }

        return helpers.createResponseEntity(true, starPlanet, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createStarPlanet(@RequestBody StarPlanet starPlanet) {
        StarPlanet savedStarPlanet = starPlanetRepository.save(starPlanet);

        return helpers.createResponseEntity(true, savedStarPlanet, null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStarPlanet(@PathVariable Integer id, @RequestBody StarPlanet starPlanet) {
        StarPlanet existingStarPlanet = starPlanetRepository.findById(id).orElse(null);

        if (existingStarPlanet == null)
            return helpers.createResponseEntity(false, null, "Star planet not found!", HttpStatus.NOT_FOUND);
    
        if (starPlanet.getPlanetTypeId() != null)
            existingStarPlanet.setPlanetTypeId(starPlanet.getPlanetTypeId());

        if (starPlanet.getSystemId() != null)
            existingStarPlanet.setSystemId(starPlanet.getSystemId());

        if (starPlanet.getAngle() != null)
            existingStarPlanet.setAngle(starPlanet.getAngle());

        if (starPlanet.getRadius() != null)
            existingStarPlanet.setRadius(starPlanet.getRadius());

        if (starPlanet.getSize() != null)
            existingStarPlanet.setSize(starPlanet.getSize());

        StarPlanet updatedStarPlanet = starPlanetRepository.save(existingStarPlanet);

        return helpers.createResponseEntity(true, updatedStarPlanet, null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteStarPlanet(@PathVariable Integer id) {
        StarPlanet starPlanet = starPlanetRepository.findById(id).orElse(null);

        if (starPlanet == null) {
            return helpers.createResponseEntity(false, null, "Star planet not found!", HttpStatus.NOT_FOUND);
        }

        starPlanetRepository.deleteById(id);

        return helpers.createResponseEntity(true, starPlanet, null, HttpStatus.OK);
    }

}
