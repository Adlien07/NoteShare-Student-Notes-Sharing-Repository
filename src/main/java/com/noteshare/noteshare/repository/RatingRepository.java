package com.noteshare.noteshare.repository;

import com.noteshare.noteshare.entity.Rating;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    boolean existsByNoteIdAndStudentId(Long noteId, Long studentId);

    // Intha line thaan puthusa add panrom (Note-oda ratings-ah delete panna)
    @Transactional
    void deleteByNoteId(Long noteId);
}