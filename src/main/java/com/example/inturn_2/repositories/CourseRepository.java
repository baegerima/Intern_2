package com.example.inturn_2.repositories;

import com.example.inturn_2.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {}

