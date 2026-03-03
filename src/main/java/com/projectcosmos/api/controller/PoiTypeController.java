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
import com.projectcosmos.api.entity.PoiType;
import com.projectcosmos.api.repository.PoiTypeRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/poi-types")
@RequiredArgsConstructor
public class PoiTypeController {
    private final PoiTypeRepository poiTypeRepository;
    private final Helpers helpers;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPoiTypes() {
        List<PoiType> poiTypes = poiTypeRepository.findAll();

        return helpers.createResponseEntity(true, poiTypes, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPoiTypeById(@PathVariable Integer id) {
        PoiType poiType = poiTypeRepository.findById(id).orElse(null);

        if (poiType == null)
            return helpers.createResponseEntity(false, null, "Poi Type not found!", HttpStatus.NOT_FOUND);

        return helpers.createResponseEntity(true, poiType, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createPoiType(@RequestBody PoiType poiType) {
        PoiType savedPoiType = poiTypeRepository.save(poiType);

        return helpers.createResponseEntity(true, savedPoiType, null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updatePoiType(@PathVariable Integer id, @RequestBody PoiType poiType) {
        PoiType existingPoiType = poiTypeRepository.findById(id).orElse(null);

        if (existingPoiType == null)
            return helpers.createResponseEntity(false, null, "Poi Type not found!", HttpStatus.NOT_FOUND);

        if (poiType.getPoiName() != null)
            existingPoiType.setPoiName(poiType.getPoiName());

        if (poiType.getPoiTypeCode() != null)
            existingPoiType.setPoiTypeCode(poiType.getPoiTypeCode());

        PoiType updatedPoiType = poiTypeRepository.save(existingPoiType);

        return helpers.createResponseEntity(true, updatedPoiType, null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletePoiType(@PathVariable Integer id) {
        PoiType poiType = poiTypeRepository.findById(id).orElse(null);

        if (poiType == null) 
            return helpers.createResponseEntity(false, null, "Poi Type not found!", HttpStatus.NOT_FOUND);

        poiTypeRepository.deleteById(id);
        return helpers.createResponseEntity(true, poiType, "Poi Type with ID" + id + " deleted successfully!", HttpStatus.OK);
    }
}
