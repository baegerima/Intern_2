package com.example.inturn_2.repositories;

import com.example.inturn_2.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.uniqueCompetences WHERE c.id = :id")
    Optional<Course> findByIdWithCompetences(@Param("id") int id);
}

