package com.noteshare.noteshare.service;

import com.noteshare.noteshare.entity.Rating;
import com.noteshare.noteshare.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Rating addRating(Rating rating) {

        // Business Rule: Student already intha note-ah rate pannirukangala nu check panrom
        boolean alreadyRated = ratingRepository.existsByNoteIdAndStudentId(
                rating.getNote().getId(),
                rating.getStudent().getId()
        );

        if (alreadyRated) {
            // Already rate panniruntha, error throw pannuvom (silently corrupt aaga koodathu nu paper la iruku)
            throw new RuntimeException("Error: You have already rated this note! A student can rate a specific note only once.");
        }

        // Rate pannala na, pudhusa save panrom
        return ratingRepository.save(rating);
    }
}