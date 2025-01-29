package com.example.inturn_2.repositories;

import com.example.inturn_2.entities.CourseSharedCompetences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseSharedCompetencesRepository extends JpaRepository<CourseSharedCompetences, Integer> {
}
