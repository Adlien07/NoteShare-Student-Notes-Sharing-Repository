package com.noteshare.noteshare.controller;

import com.noteshare.noteshare.entity.Rating;
import com.noteshare.noteshare.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    // Rating add panrathuku (POST request)
    @PostMapping("/add")
    public ResponseEntity<?> addRating(@RequestBody Rating rating) {
        try {
            // Service layer-la iruka rule check aagi rating save aagum
            Rating savedRating = ratingService.addRating(rating);
            return new ResponseEntity<>(savedRating, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Oru thadava mela rate panna try panna, intha error message thaan return aagum
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}