package com.noteshare.noteshare.controller;

import com.noteshare.noteshare.repository.NoteRepository;
import com.noteshare.noteshare.repository.RatingRepository;
import com.noteshare.noteshare.repository.StudentRepository;
import com.noteshare.noteshare.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @GetMapping("/stats")
    public Map<String, Long> getStats() {
        Map<String, Long> stats = new HashMap<>();

        // JpaRepository la iruka count() method automatic ah database la ulla rows ah count pannidum
        stats.put("totalNotes", noteRepository.count());
        stats.put("totalStudents", studentRepository.count());
        stats.put("totalSubjects", subjectRepository.count());
        stats.put("totalRatings", ratingRepository.count());

        return stats;
    }
}