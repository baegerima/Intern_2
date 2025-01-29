package com.example.inturn_2.repositories;

import com.example.inturn_2.entities.CourseUniqueCompetences;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CourseUniqueCompetencesRepository extends JpaRepository<CourseUniqueCompetences, Integer> {
    List<CourseUniqueCompetences> findByCourseId(int courseId);
}