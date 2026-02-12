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
import com.projectcosmos.api.entity.Planet;
import com.projectcosmos.api.repository.PlanetRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/planets")
@RequiredArgsConstructor
public class PlanetController {
    private final PlanetRepository planetRepository;
    private final Helpers helpers;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPlanets() {
        List<Planet> planet = planetRepository.findAll();

        return helpers.createResponseEntity(true, planet, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPlanetById(@PathVariable Integer id) {
        Planet planet = planetRepository.findById(id).orElse(null);

        if (planet == null)
            return helpers.createResponseEntity(false, null, "Planet not found", HttpStatus.NOT_FOUND);

        return helpers.createResponseEntity(true, planet, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createPlanet(@RequestBody Planet planet) {
        Planet savedPlanet = planetRepository.save(planet);

        return helpers.createResponseEntity(true, savedPlanet, null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updatePlanet(@PathVariable Integer id, @RequestBody Planet planet) {
        Planet existingPlanet = planetRepository.findById(id).orElse(null);

        if (existingPlanet == null) {
            return helpers.createResponseEntity(false, null, "Planet not found!", HttpStatus.NOT_FOUND);
        }

        if (planet.getPlanetType() != null)
            existingPlanet.setPlanetType(planet.getPlanetType());

        if (planet.getPlanetColor() != null)
            existingPlanet.setPlanetColor(planet.getPlanetColor());

        Planet updatedPlanet = planetRepository.save(existingPlanet);

        return helpers.createResponseEntity(true, updatedPlanet, null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletePlanet(@PathVariable Integer id) {
        Planet planet = planetRepository.findById(id).orElse(null);

        if (planet == null) {
            return helpers.createResponseEntity(false, null, "Planet not found", HttpStatus.NOT_FOUND);
        }

        planetRepository.deleteById(id);
        return helpers.createResponseEntity(true, planet, null, HttpStatus.OK);
    }
}
