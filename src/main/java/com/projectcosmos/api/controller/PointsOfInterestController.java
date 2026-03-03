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
import com.projectcosmos.api.entity.PointsOfInterest;
import com.projectcosmos.api.repository.PointsOfInterestRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/points-of-interest")
@RequiredArgsConstructor
public class PointsOfInterestController {
    private final PointsOfInterestRepository pointsOfInterestRepository;
    private final Helpers helpers;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPointsOfInterest() {
        List<PointsOfInterest> pointsOfInterest = pointsOfInterestRepository.findAll();

        return helpers.createResponseEntity(true, pointsOfInterest, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPointOfInterestById(@PathVariable Integer id) {
        PointsOfInterest pointOfInterest = pointsOfInterestRepository.findById(id).orElse(null);

        if (pointOfInterest == null) {
            return helpers.createResponseEntity(false, null, "Point of interest not found!", HttpStatus.NOT_FOUND);
        }

        return helpers.createResponseEntity(true, pointOfInterest, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createPointOfInterest(@RequestBody PointsOfInterest pointOfInterest) {
        PointsOfInterest savedPointOfInterest = pointsOfInterestRepository.save(pointOfInterest);

        return helpers.createResponseEntity(true, savedPointOfInterest, null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updatedPointOfInterest(@PathVariable Integer id, @RequestBody PointsOfInterest pointOfInterest) {
        PointsOfInterest existingPointOfInterest = pointsOfInterestRepository.findById(id).orElse(null);

        if (existingPointOfInterest == null)
            return helpers.createResponseEntity(false, null, "Point of interest not found!", HttpStatus.NOT_FOUND);

        if (pointOfInterest.getPoiTypeId() != null)
            existingPointOfInterest.setPoiTypeId(pointOfInterest.getPoiTypeId());

        if (pointOfInterest.getSystemId() != null)
            existingPointOfInterest.setSystemId(pointOfInterest.getSystemId());

        if (pointOfInterest.getPoiName() != null)
            existingPointOfInterest.setPoiName(pointOfInterest.getPoiName());

        PointsOfInterest updatedPointOfInterest = pointsOfInterestRepository.save(existingPointOfInterest);

        return helpers.createResponseEntity(true, updatedPointOfInterest, null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletePointOfInterest(@PathVariable Integer id) {
        PointsOfInterest pointOfInterest = pointsOfInterestRepository.findById(id).orElse(null);

        if (pointOfInterest == null)
            return helpers.createResponseEntity(false, null, "Point of interest not found!", HttpStatus.NOT_FOUND);

        pointsOfInterestRepository.deleteById(id);
        return helpers.createResponseEntity(true, pointOfInterest, null, HttpStatus.OK);
    }
}
