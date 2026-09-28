package com.noteshare.noteshare.repository;

import com.noteshare.noteshare.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // Roll No vachu student-ah kandupudika intha line thevai
    Optional<Student> findByRollNo(String rollNo);
}