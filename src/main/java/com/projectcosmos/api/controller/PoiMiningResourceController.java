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
import com.projectcosmos.api.entity.PoiMiningResource;
import com.projectcosmos.api.repository.PoiMiningResourceRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/poi-mining-resources")
@RequiredArgsConstructor
public class PoiMiningResourceController {
    private final PoiMiningResourceRepository poiMiningResourceRepository;
    private final Helpers helpers;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPoiMiningResources() {
        List<PoiMiningResource> poiMiningResources = poiMiningResourceRepository.findAll();

        return helpers.createResponseEntity(true, poiMiningResources, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPoiMiningResourceById(@PathVariable Integer id) {
        PoiMiningResource poiMiningResource = poiMiningResourceRepository.findById(id).orElse(null);

        if (poiMiningResource == null) {
            return helpers.createResponseEntity(false, null, "POI mining resource not found", HttpStatus.NOT_FOUND);
        }

        return helpers.createResponseEntity(true, poiMiningResource, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createPoiMiningResource(@RequestBody PoiMiningResource poiMiningResource) {
        
        PoiMiningResource savedPoiMiningResource = poiMiningResourceRepository.save(poiMiningResource);

        return helpers.createResponseEntity(true, savedPoiMiningResource, null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updatePoiMiningResource(@PathVariable Integer id, @RequestBody PoiMiningResource poiMiningResource) {
        PoiMiningResource existingPoiMiningResource = poiMiningResourceRepository.findById(id).orElse(null);

        if (existingPoiMiningResource == null) {
            return helpers.createResponseEntity(false, null, "POI mining resource not found", HttpStatus.NOT_FOUND);
        }

        if (poiMiningResource.getResourceId() != null) {
            existingPoiMiningResource.setResourceId(poiMiningResource.getResourceId());
        }

        if (poiMiningResource.getPoiId() != null) {
            existingPoiMiningResource.setPoiId(poiMiningResource.getPoiId());
        }

        if (poiMiningResource.getMaximum() != null) {
            existingPoiMiningResource.setMaximum(poiMiningResource.getMaximum());
        }

        if (poiMiningResource.getCurrent() != null) {
            existingPoiMiningResource.setCurrent(poiMiningResource.getCurrent());
        }

        PoiMiningResource updatedPoiMiningResource = poiMiningResourceRepository.save(existingPoiMiningResource);

        return helpers.createResponseEntity(true, updatedPoiMiningResource, null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletePoiMiningResource(@PathVariable Integer id) {
        PoiMiningResource poiMiningResource = poiMiningResourceRepository.findById(id).orElse(null);

        if (poiMiningResource == null) {
            return helpers.createResponseEntity(false, null, "POI mining resource not found", HttpStatus.NOT_FOUND);
        }

        poiMiningResourceRepository.delete(poiMiningResource);

        return helpers.createResponseEntity(true, null, "POI mining resource deleted successfully", HttpStatus.OK);
    }
}
