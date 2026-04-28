package com.projectcosmos.api.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import com.projectcosmos.api.dto.StarSystem.AddGateDto;
import com.projectcosmos.api.dto.StarSystem.StarSystemDto;
import com.projectcosmos.api.dto.StarSystem.StarSystemSummaryDto;
import com.projectcosmos.api.entity.StarSystem;
import com.projectcosmos.api.repository.StarSystemRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/star-systems")
@RequiredArgsConstructor
public class StarSystemController {
    private final StarSystemRepository starSystemRepository;
    private final Helpers helpers;

    public StarSystemDto toDto(StarSystem starSystem) {
        StarSystemDto starSystemDto = new StarSystemDto();
        starSystemDto.setId(starSystem.getId());
        starSystemDto.setGalaxy(starSystem.getGalaxy());
        starSystemDto.setHeat(starSystem.getHeat());
        starSystemDto.setPlanets(starSystem.getPlanets());
        starSystemDto.setStarRegion(starSystem.getStarRegion());
        starSystemDto.setStarType(starSystem.getStarType());
        starSystemDto.setSystemIdentifier(starSystem.getSystemIdentifier());
        starSystemDto.setSystemName(starSystem.getSystemName());
        starSystemDto.setSecurityLevel(starSystem.getSecurityLevel());
        starSystemDto.setXPos(starSystem.getXPos());
        starSystemDto.setYPos(starSystem.getYPos());
        starSystemDto.setConnectedSystems(
                starSystem.getConnectedSystems().stream()
                        .map(s -> {
                            StarSystemSummaryDto summary = new StarSystemSummaryDto();
                            summary.setId(s.getId());
                            summary.setSystemIdentifier(s.getSystemIdentifier());
                            summary.setSystemName(s.getSystemName());
                            summary.setGalaxy(s.getGalaxy());
                            summary.setSecurityLevel(s.getSecurityLevel());
                            summary.setXPos(s.getXPos());
                            summary.setYPos(s.getYPos());
                            summary.setHeat(s.getHeat());
                            summary.setStarType(s.getStarType());
                            summary.setStarRegion(s.getStarRegion());
                            summary.setPlanets(s.getPlanets());
                            return summary;
                        })
                        .collect(Collectors.toSet()));
        return starSystemDto;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStarSystems() {
        List<StarSystemDto> starSystem = starSystemRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

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

    @GetMapping("/galaxy/{galaxy}")
    public ResponseEntity<Map<String, Object>> getStarSystemByGalaxy(@PathVariable String galaxy) {
        List<StarSystemDto> starSystem = starSystemRepository.findByGalaxy(galaxy)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return helpers.createResponseEntity(true, starSystem, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createStarSystem(@RequestBody StarSystem starSystem) {

        StarSystem savedStarSystem = starSystemRepository.save(starSystem);

        return helpers.createResponseEntity(true, savedStarSystem, null, HttpStatus.CREATED);
    }

    private boolean addGate(Integer fromId, Integer toId) {
        StarSystem from = starSystemRepository.findById(fromId).orElse(null);
        StarSystem to = starSystemRepository.findById(toId).orElse(null);

        if (from == null || to == null)
            return false;

        if (!from.getGalaxy().equals(to.getGalaxy()))
            return false;

        System.out.println("HELLO?! 3");

        from.getConnectedSystems().add(to);
        starSystemRepository.save(from);

        return true;

    }

    @PostMapping("/add-gate")
    public ResponseEntity<Map<String, Object>> addBiDirectionalGate(@RequestBody AddGateDto addGateDto) {
        boolean resultA = addGate(addGateDto.getFromId(), addGateDto.getToId());
        boolean resultB = addGate(addGateDto.getToId(), addGateDto.getFromId());

        if (!resultA || !resultB) {
            return helpers.createResponseEntity(false, null, "Failed to add gate", HttpStatus.BAD_REQUEST);
        }

        return helpers.createResponseEntity(true, null, null, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStarSystem(@PathVariable Integer id,
            @RequestBody StarSystem starSystem) {
        StarSystem existingStarSystem = starSystemRepository.findById(id).orElse(null);

        if (existingStarSystem == null) {
            return helpers.createResponseEntity(false, null, "Star system not found", HttpStatus.NOT_FOUND);
        }

        if (starSystem.getSystemName() != null)
            existingStarSystem.setSystemName(starSystem.getSystemName());

        if (starSystem.getSystemIdentifier() != null)
            existingStarSystem.setSystemIdentifier(starSystem.getSystemIdentifier());

        if (starSystem.getSecurityLevel() != null)
            existingStarSystem.setSecurityLevel(starSystem.getSecurityLevel());

        if (starSystem.getGalaxy() != null)
            existingStarSystem.setGalaxy(starSystem.getGalaxy());

        if (starSystem.getXPos() != null)
            existingStarSystem.setXPos(starSystem.getXPos());

        if (starSystem.getYPos() != null)
            existingStarSystem.setYPos(starSystem.getYPos());

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
        return helpers.createResponseEntity(true, starSystem, "Star system with ID " + id + " deleted successfully",
                HttpStatus.OK);
    }
}
