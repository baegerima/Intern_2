package com.example.inturn_2.repositories;

import com.example.inturn_2.entities.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Integer> {
}