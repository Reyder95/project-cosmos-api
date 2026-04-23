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
import com.projectcosmos.api.entity.Star;
import com.projectcosmos.api.repository.StarRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/stars")
@RequiredArgsConstructor
public class StarController {
    private final StarRepository starRepository;
    private final Helpers helpers;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStars() {
        List<Star> stars = starRepository.findAll();
        return helpers.createResponseEntity(true, stars, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getStarById(@PathVariable Integer id) {
        Star star = starRepository.findById(id).orElse(null);

        if (star == null) {
            return helpers.createResponseEntity(false, null, "Star not found", HttpStatus.NOT_FOUND);
        }

        return helpers.createResponseEntity(true, star, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createStar(@RequestBody Star newStar) {
        Star savedStar = starRepository.save(newStar);
        return helpers.createResponseEntity(true, savedStar, "Star created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStar(@PathVariable Integer id, @RequestBody Star updatedStar) {
        Star star = starRepository.findById(id).orElse(null);
        if (star == null) {
            return helpers.createResponseEntity(false, null, "Star not found", HttpStatus.NOT_FOUND);
        }

        if (updatedStar.getStarName() != null)
            star.setStarName(updatedStar.getStarName());

        if (updatedStar.getStarColor() != null)
            star.setStarColor(updatedStar.getStarColor());

        Star savedStar = starRepository.save(star);
        return helpers.createResponseEntity(true, savedStar, "Star updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteStar(@PathVariable Integer id) {
        Star star = starRepository.findById(id).orElse(null);
        if (star == null) {
            return helpers.createResponseEntity(false, null, "Star not found", HttpStatus.NOT_FOUND);
        }

        starRepository.deleteById(id);
        return helpers.createResponseEntity(true, null, "Star deleted successfully", HttpStatus.OK);
    }
}
