package com.noteshare.noteshare.controller;

import com.noteshare.noteshare.entity.Student;
import com.noteshare.noteshare.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // Intha method ippo class kulla correct-aana idathula iruku
    @GetMapping("/all")
    public java.util.List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    @GetMapping("/verify/{rollNo}")
    public org.springframework.http.ResponseEntity<Student> verifyStudent(@PathVariable String rollNo) {
        java.util.Optional<Student> student = studentRepository.findByRollNo(rollNo);
        if (student.isPresent()) {
            return org.springframework.http.ResponseEntity.ok(student.get());
        } else {
            return org.springframework.http.ResponseEntity.badRequest().build();
        }
    }
}