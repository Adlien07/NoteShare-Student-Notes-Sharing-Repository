package com.noteshare.noteshare.controller;

import com.noteshare.noteshare.entity.Subject;
import com.noteshare.noteshare.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @PostMapping("/add")
    public Subject addSubject(@RequestBody Subject subject) {
        return subjectRepository.save(subject);
    }
    @GetMapping("/all")
    public java.util.List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}